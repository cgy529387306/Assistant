package com.android.mb.assistant.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsManage implements Serializable {
    private String name;
    private List<ApplyBean> dataList;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplyBean> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<ApplyBean> dataList) {
        this.dataList = dataList;
    }

    public GoodsManage(String name, List<ApplyBean> dataList) {
        this.name = name;
        this.dataList = dataList;
    }
}
