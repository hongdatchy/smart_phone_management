package com.hongdatchy.model;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

public class JsonResult {
    private String message;
    private Object data;

    public JsonResult(){}
    public JsonResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String jsonSuccess(Object data){
        return new Gson().toJson(new JsonResult( "success", data));
    }

    public String jsonFail(Object data){
        return new Gson().toJson(new JsonResult("fail", data));
    }

}
