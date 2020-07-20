package com.harshit.newsapp;

public class CardModel {
    private String image;
    private String title, src, date;

    public CardModel(String image, String title, String src, String date) {
        this.image = image;
        this.title = title;
        this.src = src;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
