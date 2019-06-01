package com.android.mb.assistant.entitys;

import java.io.Serializable;

/**
 * Created by cgy on 19/4/14.
 */

public class IType implements Serializable {

    private int drawableId;

    private String name;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public IType(String name) {
        this.name = name;
    }

    public IType(int drawableId, String name) {
        this.drawableId = drawableId;
        this.name = name;
    }
}
