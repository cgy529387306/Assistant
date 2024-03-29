package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String ctime;
    private String email;
    private String mobile;
    private String muid;//是用户id
    private int status;
    private int authority;//authority  权限0：无改派权限1：管理权限（改派、分派）2：有改派权限3：分派权限
    private String uid;//部门ID 关联z_department 表
    private String uname;
    private String upwd;
    private String userid;//用户账号
    private String usertype;
    private String departmentName;

    public String getCtime() {
        return ctime == null ? "" : ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMuid() {
        return muid == null ? "" : muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname == null ? "" : uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd == null ? "" : upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUserid() {
        return userid == null ? "" : userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype == null ? "" : usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getDepartmentName() {
        return departmentName == null ? "" : departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
