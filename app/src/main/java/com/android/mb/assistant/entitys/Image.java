package com.android.mb.assistant.entitys;

public class Image {

    private String imagePath;
    private String imageUrl;

    public String getImagePath() {
        return imagePath == null ? "" : imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(String imagePath, String imageUrl) {
        this.imagePath = imagePath;
        this.imageUrl = imageUrl;
    }
}
