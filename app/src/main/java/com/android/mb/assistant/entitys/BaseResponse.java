package com.android.mb.assistant.entitys;

import com.android.mb.assistant.utils.Helper;

import java.io.Serializable;

/**
 * Created by cgy on 19/6/27.
 */

public class BaseResponse implements Serializable{
    /**
     * 返回成功与否标识（00：成功；01：失败）
     */
    private String code;
    /**
     * 返回条数
     */
    private int total;

    /**
     * 返回信息
     */
    private String message;

    private boolean isSuccess;

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

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return Helper.isNotEmpty(code) && code.equals("00");
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
