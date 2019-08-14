package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class ApprovalListResp extends CommonListResp{
    private List<UserBean> data;

    public List<UserBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<UserBean> data) {
        this.data = data;
    }
}
