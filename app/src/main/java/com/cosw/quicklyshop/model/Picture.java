package com.cosw.quicklyshop.model;

/**
 * Created by anahisalgado on 01/09/16.
 */
public class Picture {

    private String picture;
    private String companyName;

    public Picture(String picture, String companyName) {
        this.picture = picture;
        this.companyName = companyName;
    }

    public String getPicture() { return picture; }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return companyName;
    }

    public void setUserName(String userName) {
        this.companyName = userName;
    }

}
