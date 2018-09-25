package com.lavacreators.corneliouzbett.ufarm.model;

import android.graphics.drawable.Drawable;

public class Menu {
    Drawable color;
    Drawable drawable;
    String title;

    public Menu(Drawable color, Drawable drawable, String title) {
        this.color = color;
        this.drawable = drawable;
        this.title = title;
    }

    public Menu() {
    }

    public Drawable getColor() {
        return color;
    }

    public void setColor(Drawable color) {
        this.color = color;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
