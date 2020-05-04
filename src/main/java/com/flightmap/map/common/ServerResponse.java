package com.flightmap.map.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,data info也会消失
public class ServerResponse<T> implements Serializable {

    private int status;
    private String statusInfo;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
        this.statusInfo ="";
        this.data = (T) new String[]{};

    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.statusInfo ="";
        this.data = data;
    }

    private ServerResponse(int status, String statusInfo, T data) {
        this.status = status;
        this.statusInfo = statusInfo;
        this.data = data;
    }

    private ServerResponse(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
        this.data = (T) new String[]{};
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getstatusInfo() {
        return statusInfo;
    }


    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String statusInfo) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), statusInfo);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String statusInfo, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), statusInfo, data);
    }


    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(10000+ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(10000+ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(10000 +errorCode, errorMessage);
    }

}
