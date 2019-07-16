package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class CompetitiveListResp extends CommonListResp{
    private List<CompetitiveBean> rows;

    public List<CompetitiveBean> getData() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setData(List<CompetitiveBean> data) {
        this.rows = data;
    }
}
