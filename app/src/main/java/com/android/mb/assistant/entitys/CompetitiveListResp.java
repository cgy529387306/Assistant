package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/30.
 */

public class CompetitiveListResp {
    private List<CompetitiveBean> data;

    public List<CompetitiveBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<CompetitiveBean> data) {
        this.data = data;
    }
}
