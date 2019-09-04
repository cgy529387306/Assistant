package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/27.
 */

public class ApplyDetailResp extends BaseResponse{
    private ApplyBean data;
    private List<CartBean> sqList;
    private List<SpBean> splist;

    public ApplyBean getData() {
        return data;
    }

    public void setData(ApplyBean data) {
        this.data = data;
    }

    public List<CartBean> getSqList() {
        if (sqList == null) {
            return new ArrayList<>();
        }
        return sqList;
    }

    public void setSqList(List<CartBean> sqList) {
        this.sqList = sqList;
    }

    public List<SpBean> getSplist() {
        if (splist == null) {
            return new ArrayList<>();
        }
        return splist;
    }

    public void setSplist(List<SpBean> splist) {
        this.splist = splist;
    }
}
