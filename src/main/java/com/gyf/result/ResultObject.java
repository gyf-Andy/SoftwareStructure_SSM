package com.gyf.result;

import java.io.Serializable;

public class ResultObject implements Serializable {

    //查询状态码
    private int code;
    private String msg;
    private Object data;

    public ResultObject(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObject(int code,String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
