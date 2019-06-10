package com.android.mb.assistant.entitys;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    private String ctime;
    private String email;
    private String mobile;
    private String muid;
    private int status;
    private String uid;
    private String uname;
    private String upwd;
    private String userid;
    private String usertype;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ctime);
        dest.writeString(this.email);
        dest.writeString(this.mobile);
        dest.writeString(this.muid);
        dest.writeInt(this.status);
        dest.writeString(this.uid);
        dest.writeString(this.uname);
        dest.writeString(this.upwd);
        dest.writeString(this.userid);
        dest.writeString(this.usertype);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.ctime = in.readString();
        this.email = in.readString();
        this.mobile = in.readString();
        this.muid = in.readString();
        this.status = in.readInt();
        this.uid = in.readString();
        this.uname = in.readString();
        this.upwd = in.readString();
        this.userid = in.readString();
        this.usertype = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
