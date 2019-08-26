package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class NoticeBean implements Serializable {


    /**
     * pageSize : 10
     * pageNo : 1
     * messageid : 1906256517
     * mtitle : 测试新增公告
     * mcontent : 通知公告 通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告
     * mtype : 1
     * misavailable : 0
     * muid : admin
     * mchecker : null
     * mcratetime : 2019-06-25 22:16
     * shtime : null
     */
    private String messageid;
    private String mtitle;
    private String mcontent;
    private String mtype;
    private String misavailable;
    private String muid;
    private String mcratetime;

    public String getMessageid() {
        return messageid == null ? "" : messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getMtitle() {
        return mtitle == null ? "" : mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMcontent() {
        return mcontent == null ? "" : mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getMtype() {
        return mtype == null ? "" : mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMisavailable() {
        return misavailable == null ? "" : misavailable;
    }

    public void setMisavailable(String misavailable) {
        this.misavailable = misavailable;
    }

    public String getMuid() {
        return muid == null ? "" : muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }

    public String getMcratetime() {
        return mcratetime == null ? "" : mcratetime;
    }

    public void setMcratetime(String mcratetime) {
        this.mcratetime = mcratetime;
    }
}
