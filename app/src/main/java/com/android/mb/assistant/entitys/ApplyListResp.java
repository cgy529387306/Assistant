package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class ApplyListResp extends CommonListResp{
    private List<ApplyBean> rows;

    public List<ApplyBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<ApplyBean> data) {
        this.rows = data;
    }

}
