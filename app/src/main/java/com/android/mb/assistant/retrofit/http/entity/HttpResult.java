package com.android.mb.assistant.retrofit.http.entity;

/**
 * Created by cgy on 16/3/5.
 */
public class HttpResult<T> {

    /**
     * 返回成功与否标识（200：成功；0：失败）
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回时间
     */
    private String time;

    /**
     * 返回数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
