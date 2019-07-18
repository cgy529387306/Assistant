package com.android.mb.assistant.entitys;

import java.io.Serializable;

/**
 * Created by cgy on 19/6/30.
 */

public class CompetitiveBean implements Serializable{

    /**
     * CAdd : 福州鼓楼区东街口
     * CBecomeTime : 20161512155743
     * CCreateTime : 20161512155743
     * CDispatchStatus : 2
     * CFuse : 0
     * CHandlingStatus : 2
     * CId : 2
     * CImg :
     * CImga : http://tdiff24.com:8085/image/Blur-2.png
     * CImgb :
     * CImgc :
     * CIsBroadband : 0
     * CIsOverlap : 0
     * CIschange : 0
     * CIsp : 0
     * CIsread : 0
     * CMobile : 15880062631
     * CMobileThree : 15880062633
     * CMobileTwo : 15880062632
     * CMoblieOne : 15880062631
     * CNum : 3
     * COpId : 1
     * COpName : admin
     * CRemarks :
     * CUsername : 张三
     * pageNo : 1
     * pageSize : 10
     */

    private String CAdd;
    private String cAddMoblie;
    private long CBecomeTime;
    private long cCreateTime;
    private int CDispatchStatus;
    private int CFuse;
    private int CHandlingStatus;
    private String CId;
    private String CImg;
    private int CIsBroadband;
    private int CIsOverlap;
    private int CIschange;
    private int CIsp;
    private int CIsread;
    private String CMobile;
    private int CNum;
    private int COpId;
    private String COpName;
    private String CRemarks;
    private String CUsername;

    public String getCAdd() {
        return CAdd == null ? "" : CAdd;
    }

    public void setCAdd(String CAdd) {
        this.CAdd = CAdd;
    }

    public String getcAddMoblie() {
        return cAddMoblie == null ? "" : cAddMoblie;
    }

    public void setcAddMoblie(String cAddMoblie) {
        this.cAddMoblie = cAddMoblie;
    }

    public long getCBecomeTime() {
        return CBecomeTime;
    }

    public void setCBecomeTime(long CBecomeTime) {
        this.CBecomeTime = CBecomeTime;
    }

    public long getCCreateTime() {
        return cCreateTime;
    }

    public void setCCreateTime(long CCreateTime) {
        this.cCreateTime = CCreateTime;
    }

    public int getCDispatchStatus() {
        return CDispatchStatus;
    }

    public void setCDispatchStatus(int CDispatchStatus) {
        this.CDispatchStatus = CDispatchStatus;
    }

    /**
     *  异网宽带是否融合
     * @return
     */
    public int getCFuse() {
        return CFuse;
    }

    public void setCFuse(int CFuse) {
        this.CFuse = CFuse;
    }

    public int getCHandlingStatus() {
        return CHandlingStatus;
    }

    public void setCHandlingStatus(int CHandlingStatus) {
        this.CHandlingStatus = CHandlingStatus;
    }

    public String getCId() {
        return CId == null ? "" : CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCImg() {
        return CImg == null ? "" : CImg;
    }

    public void setCImg(String CImg) {
        this.CImg = CImg;
    }

    /**
     * 是否有异网移动宽带
     * @return
     */
    public int getCIsBroadband() {
        return CIsBroadband;
    }

    public void setCIsBroadband(int CIsBroadband) {
        this.CIsBroadband = CIsBroadband;
    }

    /**
     * 是否覆盖移动宽带
     * @return
     */
    public int getCIsOverlap() {
        return CIsOverlap;
    }

    public void setCIsOverlap(int CIsOverlap) {
        this.CIsOverlap = CIsOverlap;
    }


    public int getCIschange() {
        return CIschange;
    }

    public void setCIschange(int CIschange) {
        this.CIschange = CIschange;
    }

    /**
     *  异网宽带运营商0：电信1：联通2：其他
     * @return
     */
    public int getCIsp() {
        return CIsp;
    }

    public void setCIsp(int CIsp) {
        this.CIsp = CIsp;
    }

    /**
     *  是否已读
     * @return
     */
    public int getCIsread() {
        return CIsread;
    }

    public void setCIsread(int CIsread) {
        this.CIsread = CIsread;
    }

    public String getCMobile() {
        return CMobile == null ? "" : CMobile;
    }

    public void setCMobile(String CMobile) {
        this.CMobile = CMobile;
    }

    public int getCNum() {
        return CNum;
    }

    public void setCNum(int CNum) {
        this.CNum = CNum;
    }

    public int getCOpId() {
        return COpId;
    }

    public void setCOpId(int COpId) {
        this.COpId = COpId;
    }

    public String getCOpName() {
        return COpName == null ? "" : COpName;
    }

    public void setCOpName(String COpName) {
        this.COpName = COpName;
    }

    public String getCRemarks() {
        return CRemarks == null ? "" : CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public String getCUsername() {
        return CUsername == null ? "" : CUsername;
    }

    public void setCUsername(String CUsername) {
        this.CUsername = CUsername;
    }
}
