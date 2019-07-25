package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class GoodsListResp extends CommonListResp{
    private List<GoodsBean> rows;

    public List<GoodsBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<GoodsBean> data) {
        this.rows = data;
    }
}
