package com.android.mb.assistant.entitys;

import java.util.ArrayList;
import java.util.List;

public class HomeData extends BaseResponse{

    private List<BannerBean> bannerList;

    private List<NoticeBean> bulletinList;

    public List<BannerBean> getBannerList() {
        if (bannerList == null) {
            return new ArrayList<>();
        }
        return bannerList;
    }

    public void setBannerList(List<BannerBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<NoticeBean> getBulletinList() {
        if (bulletinList == null) {
            return new ArrayList<>();
        }
        return bulletinList;
    }

    public void setBulletinList(List<NoticeBean> bulletinList) {
        this.bulletinList = bulletinList;
    }
}
