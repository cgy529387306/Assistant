package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class PatternListResp extends CommonListResp{
    private List<PatternBean> rows;

    public List<PatternBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<PatternBean> data) {
        this.rows = data;
    }
}
