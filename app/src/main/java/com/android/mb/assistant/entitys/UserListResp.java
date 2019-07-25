package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class UserListResp extends CommonListResp{
    private List<UserBean> rows;

    public List<UserBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<UserBean> data) {
        this.rows = data;
    }
}
