package com.frs.demo.pojo;

public class Face {
    private String faceId;
    private String userName;
    private String idCard;
    private int age;

    public Face() {
    }

    public Face(String faceId, String userName, String idCard, int number) {
        this.faceId = faceId;
        this.userName = userName;
        this.idCard = idCard;
        this.age = number;
    }

    public String getFaceId() {
        return faceId;
    }

    public String getUserName() {
        return userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getNumber() {
        return age;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setNumber(int number) {
        this.age = number;
    }
}
