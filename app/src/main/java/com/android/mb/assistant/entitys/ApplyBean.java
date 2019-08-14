package com.android.mb.assistant.entitys;

import java.io.Serializable;

/**
 * Created by cgy on 19/8/14.
 */

public class ApplyBean implements Serializable{

    /**
     * pageSize : 10
     * pageNo : 1
     * applicationId : 19
     * apSqId : 1
     * apSqName : 1
     * apTel : 18650480850
     * apTime : null
     * apSyTime : null
     * apGfTime : null
     * apApprover : 菜
     * apRemarks : 666666
     * apStatus : 0
     * apSyStatus : null
     * apCause :
     */

    private String applicationId;
    private String apSqId;
    private String apSqName;
    private String apTel;
    private String apTime;
    private String apSyTime;
    private String apGfTime;
    private String apApprover;
    private String apRemarks;
    private int apStatus;
    private String apSyStatus;
    private String apCause;

    public String getApplicationId() {
        return applicationId == null ? "" : applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApSqId() {
        return apSqId == null ? "" : apSqId;
    }

    public void setApSqId(String apSqId) {
        this.apSqId = apSqId;
    }

    public String getApSqName() {
        return apSqName == null ? "" : apSqName;
    }

    public void setApSqName(String apSqName) {
        this.apSqName = apSqName;
    }

    public String getApTel() {
        return apTel == null ? "" : apTel;
    }

    public void setApTel(String apTel) {
        this.apTel = apTel;
    }

    public String getApTime() {
        return apTime == null ? "" : apTime;
    }

    public void setApTime(String apTime) {
        this.apTime = apTime;
    }

    public String getApSyTime() {
        return apSyTime == null ? "" : apSyTime;
    }

    public void setApSyTime(String apSyTime) {
        this.apSyTime = apSyTime;
    }

    public String getApGfTime() {
        return apGfTime == null ? "" : apGfTime;
    }

    public void setApGfTime(String apGfTime) {
        this.apGfTime = apGfTime;
    }

    public String getApApprover() {
        return apApprover == null ? "" : apApprover;
    }

    public void setApApprover(String apApprover) {
        this.apApprover = apApprover;
    }

    public String getApRemarks() {
        return apRemarks == null ? "" : apRemarks;
    }

    public void setApRemarks(String apRemarks) {
        this.apRemarks = apRemarks;
    }

    public int getApStatus() {
        return apStatus;
    }

    public void setApStatus(int apStatus) {
        this.apStatus = apStatus;
    }

    public String getApSyStatus() {
        return apSyStatus == null ? "" : apSyStatus;
    }

    public void setApSyStatus(String apSyStatus) {
        this.apSyStatus = apSyStatus;
    }

    public String getApCause() {
        return apCause == null ? "" : apCause;
    }

    public void setApCause(String apCause) {
        this.apCause = apCause;
    }
}
