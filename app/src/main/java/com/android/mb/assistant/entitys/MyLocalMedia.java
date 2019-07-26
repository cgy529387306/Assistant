package com.android.mb.assistant.entitys;

import com.luck.picture.lib.entity.LocalMedia;

/**
 * Created by cgy on 19/7/26.
 */

public class MyLocalMedia extends LocalMedia{
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
