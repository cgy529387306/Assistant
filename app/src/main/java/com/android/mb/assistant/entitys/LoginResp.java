package com.android.mb.assistant.entitys;

/**
 * Created by cgy on 19/6/27.
 */

public class LoginResp extends BaseResponse{

    private UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
