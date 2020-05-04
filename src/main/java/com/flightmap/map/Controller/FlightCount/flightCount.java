package com.flightmap.map.Controller.FlightCount;


import com.flightmap.map.Pojo.MapCountall;
import com.flightmap.map.common.CustomException.EmBusinessError;
import com.flightmap.map.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.flightmap.map.Service.IFlightAllCountService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/flightCount/")
public class flightCount {
    @Autowired
    private IFlightAllCountService iFlightAllCountService;

    @RequestMapping(value = "CurrentDataWorld",method = RequestMethod.POST)
    @ResponseBody //序列化为json
    public ServerResponse<MapCountall> currentDataWorld()  {
        return  iFlightAllCountService.currentDataWorld();

}

}








