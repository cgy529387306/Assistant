package com.android.mb.assistant.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsManage implements Serializable {
    private String name;
    private List<ApplyBean> dataList;
    private List<GoodsBean> goodsList;

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

    public List<GoodsBean> getGoodsList() {
        if (goodsList == null) {
            return new ArrayList<>();
        }
        return goodsList;
    }

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public GoodsManage(String name, List<ApplyBean> dataList, List<GoodsBean> goodsList) {
        this.name = name;
        this.dataList = dataList;
        this.goodsList = goodsList;
    }
}
