package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class DicListResp extends CommonListResp{
    private List<DicBean> rows;

    public List<DicBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<DicBean> data) {
        this.rows = data;
    }
}
