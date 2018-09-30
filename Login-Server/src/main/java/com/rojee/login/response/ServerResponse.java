package com.rojee.login.response;

import com.google.gson.JsonObject;

public enum ServerResponse {
    Success(0,"成功"),
    EmptyAccountOrPassword(1001,"用户名或密码为空"),
    InvalidAccountOrPassword(1002,"用户名或密码错误"),
    PasswordLengthNotEnough(1003,"密码长度不够"),
    AccountAlreadyExists(1004,"用户名已经存在");

    private int errCode;

    private String errMsg;

    public int getErrCode(){
        return errCode;
    }

    public String getErrMsg(){
        return errMsg;
    }

    ServerResponse(int i, String msg) {
        errCode = i;
        errMsg = msg;
    }

    public static String buildResponse(ServerResponse serverResponse){
        JsonObject object = new JsonObject();
        object.addProperty("errcode", serverResponse.getErrCode());
        object.addProperty("errmsg", serverResponse.getErrMsg());
        return object.toString();
    }
}
