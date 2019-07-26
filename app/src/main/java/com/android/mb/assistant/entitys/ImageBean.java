package com.android.mb.assistant.entitys;

import java.io.Serializable;

/**
 * Created by cgy on 19/7/26.
 */

public class ImageBean implements Serializable{

    private String imgs0;

    public String getImages0() {
        return imgs0 == null ? "" : imgs0;
    }

    public void setImages0(String images0) {
        this.imgs0 = images0;
    }
}
