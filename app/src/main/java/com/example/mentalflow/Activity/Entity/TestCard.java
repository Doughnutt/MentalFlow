package com.example.mentalflow.Activity.Entity;

import java.io.Serializable;

public class TestCard implements Serializable {
    private int id;//测试id
    private int category; //测试类别
    private String testName;//测试名称
    private int imageId;//测试图片id
    private int bgId;//测试背景卡片id

    public TestCard(int id, int category, int imageId) {
        this.id = id;
        this.category = category;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getBgId() {
        return bgId;
    }

    public void setBgId(int bgId) {
        this.bgId = bgId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
