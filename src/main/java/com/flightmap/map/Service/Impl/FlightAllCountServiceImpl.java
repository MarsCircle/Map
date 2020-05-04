package com.flightmap.map.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.flightmap.map.Dao.MapCountallMapper;
import com.flightmap.map.Pojo.MapCountall;
import com.flightmap.map.Service.IFlightAllCountService;
import com.flightmap.map.Util.postSend;
import com.flightmap.map.common.CustomException.EmBusinessError;
import com.flightmap.map.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("iFlightAllCountService")
public class FlightAllCountServiceImpl implements IFlightAllCountService {

    @Autowired
    private MapCountallMapper mapCountallMapper;


    @Scheduled(fixedDelay = 2000)
    @Override
    public void flightCount() throws ParseException {
        System.out.println("查询开始");
        String uriString = "https://api.ifengge.cn/adsb/live/?";
        postSend postSendUtil = new postSend();
        //北半球拆分查询 防止超过1500
        JSONArray jsonArrayNorth = postSendUtil.postSend1(uriFactory(uriString, 0, 40, -180, 180));
        JSONArray jsonArrayNorth1 = postSendUtil.postSend1(uriFactory(uriString, 40, 90, -180, 180));
        int northCount = jsonArrayNorth.size() + jsonArrayNorth1.size();
        JSONArray jsonArraySouth = postSendUtil.postSend1(uriFactory(uriString, -90, 0, -180, 180));
        JSONArray jsonArrayAllCurrent1 = new JSONArray();
        jsonArrayAllCurrent1.add(jsonArrayNorth);
        jsonArrayAllCurrent1.add(jsonArrayNorth1);
        jsonArrayAllCurrent1.add(jsonArraySouth);
        String str1 = jsonArrayAllCurrent1.toString();
        str1 = JsonDataFactory(str1);
        JSONArray jsonArrayAllCurrent = JSONArray.parseArray(str1);


        //获取时间 判断当天是否已有数据
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dateToday = df.format(new Date());
        MapCountall mapCountall = mapCountallMapper.selectByTime(dateToday);
        if (mapCountall != null) {
            String JsonDataToday = mapCountall.getJsonDataToday();
            String JsonDataCurrent = mapCountall.getJsonDataCurrent();
            JsonDataToday = JsonDataFactory(JsonDataToday);
            JsonDataCurrent = JsonDataFactory(JsonDataCurrent);
            JSONArray JsonDataToday_Json = JSONArray.parseArray(JsonDataToday);
            JSONArray JsonDataCurrent_Json = JSONArray.parseArray(JsonDataCurrent);
            JSONArray JsonDataToday_Json_update = countAdd(JsonDataToday_Json, JsonDataCurrent_Json);
            mapCountall.setJsonDataCurrent(jsonArrayAllCurrent.toString());
            mapCountall.setJsonDataToday(JsonDataToday_Json_update.toString());
            mapCountall.setCountCurrent(String.valueOf(jsonArrayAllCurrent.size()));
            mapCountall.setAllCountToday(String.valueOf(JsonDataToday_Json_update.size()));
            String jsonDataCurrent = mapCountall.getJsonDataCurrent();
            String jsonDataToday = mapCountall.getJsonDataToday();
            String countCurrent = mapCountall.getCountCurrent();
            String allCountToday = mapCountall.getAllCountToday();
            mapCountallMapper.updateByTime(jsonDataCurrent, jsonDataToday, allCountToday, countCurrent, dateToday);
        } else {
            MapCountall mapCountallNew = new MapCountall(jsonArrayAllCurrent.toString(), jsonArrayAllCurrent.toString(), String.valueOf(jsonArrayAllCurrent.size()), String.valueOf(jsonArrayAllCurrent.size()));
            mapCountallMapper.insert(mapCountallNew);
        }


    }

    //生成请求的url
    private URI uriFactory(String uriString, double lamin, double lamax, double lomin, double lomax) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                uriString + "lamin=" + lamin + "&lamax=" + lamax + "&lomin=" + lomin + "&lomax=" + lomax)
                .build()
                .encode();
        return uriComponents.toUri();
    }

    //起飞降落机场计数
    private HashMap<String, String> countAirport(JSONArray jsonArray) {
        int numberArr;//降落机场数
        int numberDep;//起飞机场数
        HashMap<String, Integer> airportArr = new HashMap<>();
        HashMap<String, Integer> airportDep = new HashMap<>();

        Set<JSONObject> countAirport = new HashSet<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo = new JSONObject();
            jo.put("arr", jsonArray.getJSONObject(i).get("arr"));
            String arr = (String) jsonArray.getJSONObject(i).get("arr");
            if(arr==null){
                continue;
            }
            if (!countAirport.add(jo)) {
                numberArr = airportArr.get(arr);
                airportArr.replace(arr, numberArr + 1);
            } else {
                airportArr.put(arr, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(airportArr.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //按照value值，从大到小排序
                return o2.getValue() - o1.getValue();
            }
        });
        HashMap<String,String> allCount = new HashMap<>();
        String str = list.get(0).getKey()+list.get(0).toString();
        allCount.put("arr",str);
        return allCount;

    }



    public JSONArray countAdd(JSONArray jsonArrayOrigin, JSONArray jsonArrayNew){
        //除去每次查询相同的飞机
        int number = 0;
        Set<JSONObject> temp = new HashSet<JSONObject>();
        for (int i = 0; i < jsonArrayOrigin.size(); i++) {
            JSONObject jo=new JSONObject();

            jo.put("icao",jsonArrayOrigin.getJSONObject(i).get("icao"));
            jo.put("dep",jsonArrayOrigin.getJSONObject(i).get("dep"));
            jo.put("arr",jsonArrayOrigin.getJSONObject(i).get("arr"));
            temp.add(jo);
        }


        for (int j = 0; j < jsonArrayNew.size(); j++) {
            // 把数组b中的元素添加到temp中
            String str  = jsonArrayNew.toString();
            JSONObject jo=new JSONObject();

            jo.put("icao",jsonArrayNew.getJSONObject(j).get("icao")); // 把数组a中的元素放到Set中，可以去除重复的元素
            jo.put("dep", jsonArrayNew.getJSONObject(j).get("dep"));
            jo.put("arr", jsonArrayNew.getJSONObject(j).get("arr"));
            // 如果temp中已存在相同的元素，则temp.add（b[j]）返回false
            if (!temp.add(jo)) {
                number++;
            }
        }
        JSONArray JsonDataToday_Json = new JSONArray();
        JsonDataToday_Json.addAll(temp);
        return JsonDataToday_Json;
    }
    public String JsonDataFactory(String str){
        str=str.replace("[", "");
        str=str.replace("]", "");
        str="["+str+"]";
        return str;
    }


    @Override
    public ServerResponse<MapCountall> currentDataWorld()  {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dateToday = df.format(new Date());
        MapCountall mapCountall = mapCountallMapper.selectByTime(dateToday);
        return ServerResponse.createBySuccess("查找成功", mapCountall);
    }
}
