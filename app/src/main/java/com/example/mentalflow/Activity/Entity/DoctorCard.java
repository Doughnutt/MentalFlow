package com.example.mentalflow.Activity.Entity;

public class DoctorCard {
        private int id;//医生id
        private String docName;//医生名称
        private int imageId;//医生图片id
        private String type;//医生擅长类型
        private String intro;//医生介绍
        public DoctorCard(){
        }

        public DoctorCard(String docName, int imageId, String type, String intro) {
            this.docName = docName;
            this.imageId = imageId;
            this.type = type;
            this.intro = intro;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }

