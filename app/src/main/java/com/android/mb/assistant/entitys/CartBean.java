package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class CartBean implements Serializable {

    /**
     * cartId : 2
     * materialNum : 1
     * materialId : 43
     * materialName : 笔记本电脑
     * time : 1565663550694
     * materialPrice : 10000.0
     * memberId : 1
     * isDel : 0
     * isApplicate : 0
     */

    private String cartId;
    private int materialNum;
    private String materialId;
    private String materialName;
    private long time;
    private double materialPrice;
    private String memberId;
    private String mGsDepartment;
    private String mImg;
    private int isDel;
    private int isApplicate;
    private boolean isSelect;

    public String getCartId() {
        return cartId == null ? "" : cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(int materialNum) {
        this.materialNum = materialNum;
    }

    public String getMaterialId() {
        return materialId == null ? "" : materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName == null ? "" : materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(double materialPrice) {
        this.materialPrice = materialPrice;
    }

    public String getMemberId() {
        return memberId == null ? "" : memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getIsApplicate() {
        return isApplicate;
    }

    public void setIsApplicate(int isApplicate) {
        this.isApplicate = isApplicate;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getGsDepartment() {
        return mGsDepartment == null ? "" : mGsDepartment;
    }

    public void setGsDepartment(String gsDepartment) {
        mGsDepartment = gsDepartment;
    }

    public String getImg() {
        return mImg == null ? "" : mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }
}
