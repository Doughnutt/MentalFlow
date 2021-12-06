package com.example.mentalflow.Activity.Entity;

public class ArticleCard {
    private int id;//文章id
    private String articleName;//文章名称
    private int imageId;//文章图片id
    private String label;//文章类型
    private String intro;//文章介绍

    public ArticleCard(String articleName, int imageId, String label, String intro) {
        this.articleName = articleName;
        this.imageId = imageId;
        this.label = label;
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
