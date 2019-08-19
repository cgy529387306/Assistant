package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/27.
 */

public class ApplyDetailResp extends BaseResponse{
    private ApplyBean data;
    private List<CartBean> rows;

    public ApplyBean getData() {
        return data;
    }

    public void setData(ApplyBean data) {
        this.data = data;
    }

    public List<CartBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<CartBean> rows) {
        this.rows = rows;
    }
}
