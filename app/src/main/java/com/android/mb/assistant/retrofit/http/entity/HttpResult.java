package com.android.mb.assistant.retrofit.http.entity;

/**
 * Created by cgy on 16/3/5.
 */
public class HttpResult<T> {

    /**
     * 返回成功与否标识（00：成功；01：失败）
     */
    private String code;
    /**
     * 返回条数
     */
    private int total;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回信息
     */
    private String message;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
