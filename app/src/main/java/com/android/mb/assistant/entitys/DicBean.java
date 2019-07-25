package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class DicBean implements Serializable {


    /**
     * dictname : 设计
     * dictno : 9
     * dictvalue : 设计
     * haveChil :
     * id : 13
     * isfixed :
     * level :
     * parno : 12
     * remark :
     */

    private String dictname;
    private String dictno;
    private String dictvalue;
    private String haveChil;
    private String id;
    private String isfixed;
    private String level;
    private String parno;
    private String remark;

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname;
    }

    public String getDictno() {
        return dictno;
    }

    public void setDictno(String dictno) {
        this.dictno = dictno;
    }

    public String getDictvalue() {
        return dictvalue;
    }

    public void setDictvalue(String dictvalue) {
        this.dictvalue = dictvalue;
    }

    public String getHaveChil() {
        return haveChil;
    }

    public void setHaveChil(String haveChil) {
        this.haveChil = haveChil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsfixed() {
        return isfixed;
    }

    public void setIsfixed(String isfixed) {
        this.isfixed = isfixed;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParno() {
        return parno;
    }

    public void setParno(String parno) {
        this.parno = parno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
