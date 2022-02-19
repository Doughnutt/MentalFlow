package com.example.mentalflow.Activity.Entity;

import java.io.Serializable;

public class ArticleCard implements Serializable {
    private int id;//文章id
    private String title;//文章名称
    private int imageId;//文章图片id
    private String label;//文章类型
    private String content;//文章介绍
    private String date;//文章发布日期
    private String ref;//文章发布来源


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public ArticleCard(){

    }
    public ArticleCard(String title,int imageId,String label, String content) {
        this.title = title;
        this.imageId=imageId;
        this.label = label;
        this.content = content;
    }
    public ArticleCard(String title, String label, String content, String date, String ref){
        this.title = title;
        this.label = label;
        this.content = content;
        this.date=date;
        this.ref=ref;
    }
}
