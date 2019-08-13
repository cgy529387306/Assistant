package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class GoodsBean implements Serializable {

    /**
     * mAsset : 1
     * mContacts : 大结局息怒息怒
     * mCreateTime : 20190701225700
     * mGsDepartment : 设计部
     * mGsDepartmentId : 0
     * mImg : 无
     * mMaterialName : 也合适的
     * mMaterialType : 1
     * mMum : 11
     * mPattern : 同意天天
     * mRemaks :
     * mStatus : 0
     * mStorageSite : 大结局小鸡小鸡
     * mStorageSiteId : 0
     * mUnitname : 春华科教园
     * mUnitnameId : 0
     * materialId : 28
     * pageNo : 1
     * pageSize : 10
     */

    private int mAsset;
    private String mContacts;
    private long mCreateTime;
    private String mGsDepartment;
    private String mGsDepartmentId;
    private String mImg;
    private String mMaterialName;
    private String mMaterialType;
    private int mMum;
    private String mPattern;
    private String mRemaks;
    private int mStatus;
    private String mStorageSite;
    private String mStorageSiteId;
    private String mUnitname;
    private String mUnitnameId;
    private String materialId;
    private String mPrice;
    private int mAddMum;

    public int getAsset() {
        return mAsset;
    }

    public void setAsset(int asset) {
        mAsset = asset;
    }

    public String getContacts() {
        return mContacts == null ? "" : mContacts;
    }

    public void setContacts(String contacts) {
        mContacts = contacts;
    }

    public long getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(long createTime) {
        mCreateTime = createTime;
    }

    public String getGsDepartment() {
        return mGsDepartment == null ? "" : mGsDepartment;
    }

    public void setGsDepartment(String gsDepartment) {
        mGsDepartment = gsDepartment;
    }

    public String getGsDepartmentId() {
        return mGsDepartmentId == null ? "" : mGsDepartmentId;
    }

    public void setGsDepartmentId(String gsDepartmentId) {
        mGsDepartmentId = gsDepartmentId;
    }

    public String getImg() {
        return mImg == null ? "" : mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    public String getMaterialName() {
        return mMaterialName == null ? "" : mMaterialName;
    }

    public void setMaterialName(String materialName) {
        mMaterialName = materialName;
    }


    public int getMum() {
        return mMum;
    }

    public void setMum(int mum) {
        mMum = mum;
    }

    public String getPattern() {
        return mPattern == null ? "" : mPattern;
    }

    public void setPattern(String pattern) {
        mPattern = pattern;
    }

    public String getRemaks() {
        return mRemaks == null ? "" : mRemaks;
    }

    public void setRemaks(String remaks) {
        mRemaks = remaks;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getStorageSite() {
        return mStorageSite == null ? "" : mStorageSite;
    }

    public void setStorageSite(String storageSite) {
        mStorageSite = storageSite;
    }

    public String getStorageSiteId() {
        return mStorageSiteId == null ? "" : mStorageSiteId;
    }

    public void setStorageSiteId(String storageSiteId) {
        mStorageSiteId = storageSiteId;
    }

    public String getUnitname() {
        return mUnitname == null ? "" : mUnitname;
    }

    public void setUnitname(String unitname) {
        mUnitname = unitname;
    }

    public String getUnitnameId() {
        return mUnitnameId == null ? "" : mUnitnameId;
    }

    public void setUnitnameId(String unitnameId) {
        mUnitnameId = unitnameId;
    }

    public String getMaterialId() {
        return materialId == null ? "" : materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialType() {
        return mMaterialType == null ? "" : mMaterialType;
    }

    public void setMaterialType(String materialType) {
        mMaterialType = materialType;
    }

    public String getPrice() {
        return mPrice == null ? "" : mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public int getAddMum() {
        return mAddMum==0?1:mAddMum;
    }

    public void setAddMum(int addMum) {
        mAddMum = addMum;
    }
}
