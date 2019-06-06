package com.android.mb.assistant.retrofit.http.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 16/3/5.
 */
public class HttpResult<T> {

    /**
     * 返回成功与否标识（00：成功；01：失败）
     */
    private int code;
    /**
     * 返回条数
     */
    private int total;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回列表
     */
    private List<T> rows;
    /**
     * 返回信息
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public List<T> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
