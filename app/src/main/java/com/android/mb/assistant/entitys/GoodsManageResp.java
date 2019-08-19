package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgy on 19/6/27.
 */

public class GoodsManageResp extends BaseResponse{
    private List<ApplyBean> spList;
    private List<ApplyBean> sqList;
    private List<GoodsBean> zxList;

    public List<ApplyBean> getSpList() {
        if (spList == null) {
            return new ArrayList<>();
        }
        return spList;
    }

    public void setSpList(List<ApplyBean> spList) {
        this.spList = spList;
    }

    public List<ApplyBean> getSqList() {
        if (sqList == null) {
            return new ArrayList<>();
        }
        return sqList;
    }

    public void setSqList(List<ApplyBean> sqList) {
        this.sqList = sqList;
    }


    public List<GoodsBean> getZxList() {
        if (zxList == null) {
            return new ArrayList<>();
        }
        return zxList;
    }

    public void setZxList(List<GoodsBean> zxList) {
        this.zxList = zxList;
    }
}
