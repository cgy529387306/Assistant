package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class CityBean implements Serializable {


    /**
     * areaId : 1
     * areaName : 宣州区
     * dDepartmentName : 鼓楼一部
     * dStatus : 0
     * pageNo : 1
     * pageSize : 10
     */

    private String areaId;
    private String areaName;
    private String dDepartmentName;
    private String dStatus;

    public String getAreaId() {
        return areaId == null ? "" : areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName == null ? "" : areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getdDepartmentName() {
        return dDepartmentName == null ? "" : dDepartmentName;
    }

    public void setdDepartmentName(String dDepartmentName) {
        this.dDepartmentName = dDepartmentName;
    }

    public String getdStatus() {
        return dStatus == null ? "" : dStatus;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }
}
