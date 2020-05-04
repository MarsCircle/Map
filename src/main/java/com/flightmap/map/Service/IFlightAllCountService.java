package com.flightmap.map.Service;

import com.flightmap.map.Pojo.MapCountall;
import com.flightmap.map.common.ServerResponse;

import java.text.ParseException;

public interface IFlightAllCountService {

    public void  flightCount() throws ParseException;

    ServerResponse<MapCountall> currentDataWorld();
}
