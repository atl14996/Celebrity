package com.example.week6day3homework;

public class Celebrity {
private String picture;
private String name;
private boolean isFavorite;

    public Celebrity(String picture, String name, boolean isFavorite) {
        this.picture = picture;
        this.name = name;
        this.isFavorite = isFavorite;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
