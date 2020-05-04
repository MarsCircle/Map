package com.flightmap.map.common.CustomException;

public enum EmBusinessError implements CommonError {

    EMAIL_NULL_ERROR(20001,"所输入邮箱不可为空"),



    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg ) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }



    @Override
    public int getErrorCode() {
        return this.errCode;
    }


    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }


    @Override
    public CommonError setErrorMsg(String ErrorMsg) {
        this.errMsg = ErrorMsg;
        return this;
    }

}