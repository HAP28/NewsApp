package com.harshit.newsapp;

public class ScreenItem {
    String Title;
    int ScreenImg;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public ScreenItem(String title, int screenImg) {
        Title = title;
        ScreenImg = screenImg;
    }
}
