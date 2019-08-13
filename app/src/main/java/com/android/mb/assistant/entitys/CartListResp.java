package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class CartListResp extends CommonListResp{
    private List<CartBean> rows;

    public List<CartBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<CartBean> data) {
        this.rows = data;
    }
}
