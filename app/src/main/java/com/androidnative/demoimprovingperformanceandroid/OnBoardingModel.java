package com.androidnative.demoimprovingperformanceandroid;

import androidx.annotation.DrawableRes;

public class OnBoardingModel {
    private int text;
    @DrawableRes int  image;
    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public OnBoardingModel(int text, int image) {
        this.text = text;
        this.image = image;
    }

    public int compareTo(OnBoardingModel element) {
        int res = 0;
        if (this.getText() < element.getText()) {
            res =- 1;
        }
        if (this.getText() > element.getText()) {
            res = 1;
        }
        return res;
    }
}
