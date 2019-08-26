package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class BannerBean implements Serializable {


    /**
     * pageSize : 10
     * pageNo : 1
     * bannerId : 1
     * bannerPicUrl : http://114.115.136.72:8080/MoveHelper/image/1566287047455.jpg
     * isDel : 0
     */
    private String bannerId;
    private String bannerPicUrl;
    private int isDel;

    public String getBannerId() {
        return bannerId == null ? "" : bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl == null ? "" : bannerPicUrl;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
