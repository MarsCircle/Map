package com.flightmap.map.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


public class postSend{
    public JSONArray postSend1(URI uri){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host","api.ifengge.cn");
        String result;
        while(true) {
            try {
                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        new HttpEntity<String>(headers),
                        String.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    result = responseEntity.getBody();
                    break;
                }
                System.out.println("查询正在重试");
            } catch (Exception e) {
                System.out.println("查询正在重试");
                continue;
            }
        }

        JSONObject jsonObject  = JSON.parseObject(result);
        return jsonObject.getJSONArray("states");
    }
}



//public class ResponseUtil {
//
//    public static String postLoad(String url, String query,String token) throws Exception {
//        URL restURL = new URL(url);
//        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
//        //请求方式
//        conn.setRequestMethod("POST");
//        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
//        conn.setDoOutput(false);
//        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
//        conn.setAllowUserInteraction(false);
//        conn.setRequestProperty("token", token);
//        conn.setRequestProperty("Content-type", "application/json");
//        //conn.connect();
//        PrintStream ps = new PrintStream(conn.getOutputStream());
//        ps.print(query);
//        ps.close();
//        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        StringBuilder resultStr = new StringBuilder();
//        while (null != (line = bReader.readLine())) {
//            resultStr.append(line);
//        }
//        System.out.println("---" + resultStr);
//        bReader.close();
//        return resultStr.toString();
//    }
//}
//
//
////    public static String httpPost(String urlStr,Map<String,String> params){
////        URL connect;
////        StringBuffer data = new StringBuffer();
////
////            OutputStreamWriter paramout = new OutputStreamWriter(
////                    connection.getOutputStream(),"UTF-8");
////            String paramsStr = "";   //拼接Post 请求的参数
////            for(String param : params.keySet()){
////                paramsStr += "&" + param + "=" + params.get(param);
////            }
////            if(!paramsStr.isEmpty()){
////                paramsStr = paramsStr.substring(1);
////            }
////            paramout.write(paramsStr);
////            paramout.flush();
////            BufferedReader reader = new BufferedReader(new InputStreamReader(
////                    connection.getInputStream(), "UTF-8"));
////            String line;
////            while ((line = reader.readLine()) != null) {
////                data.append(line);
////            }
////
////            paramout.close();
////            reader.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return data.toString();
////    }
////
//
//
