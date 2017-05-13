package com.example.listviewtest;

/**
 * Created by shush on 2017/5/12.
 */

public class Fruit {
    private String name;
    private int imageId;

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Fruit(String name, int imageId) {

        this.name = name;
        this.imageId = imageId;
    }
}
