package com.firstapp;

/**
 * Created by stgqq on 2017/3/29.
 */

public class Listitem {
    private String name;
    private int imageId;
    public Listitem(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
