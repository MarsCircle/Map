package com.flightmap.map.Pojo;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Notice {
    private int status;
    private Object msg;

    public JsonAlias getData() {
        return data;
    }

    public void setData(JsonAlias data) {
        this.data = data;
    }

    private JsonAlias data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}

