package com.harshit.newsapp;

import android.widget.ImageView;

public class CardModel {
    private String image;
    private String title,dec;

    public CardModel(String image, String title, String dec) {
        this.image = image;
        this.title = title;
        this.dec = dec;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
