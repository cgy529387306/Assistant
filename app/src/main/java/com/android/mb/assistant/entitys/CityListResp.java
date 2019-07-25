package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class CityListResp extends CommonListResp{
    private List<CityBean> rows;

    public List<CityBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<CityBean> data) {
        this.rows = data;
    }
}
