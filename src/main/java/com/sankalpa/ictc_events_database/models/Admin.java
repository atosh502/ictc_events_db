package com.sankalpa.ictc_events_database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Admin{

    private Long userId;
    private String userName;
    private String userPassword;

    public Admin(){}

    public Admin(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Admin(Long userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}