package com.sankalpa.ictc_events_database.models;

public class Organizer {

    private Long userId;
    private String userName;
    private String userPassword;
    private String organizerName;
    private String organizerEmail;
    private String organizerAddress;
    private String organizerPhone;

    public Organizer(){}

    public Organizer(String userName, String userPassword, String organizerName, String organizerEmail, String organizerAddress, String organizerPhone) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.organizerName = organizerName;
        this.organizerEmail = organizerEmail;
        this.organizerAddress = organizerAddress;
        this.organizerPhone = organizerPhone;
    }

    public Organizer(Long userId, String userName, String userPassword, String organizerName, String organizerEmail, String organizerAddress, String organizerPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.organizerName = organizerName;
        this.organizerEmail = organizerEmail;
        this.organizerAddress = organizerAddress;
        this.organizerPhone = organizerPhone;
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

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public String getOrganizerAddress() {
        return organizerAddress;
    }

    public void setOrganizerAddress(String organizerAddress) {
        this.organizerAddress = organizerAddress;
    }

    public String getOrganizerPhone() {
        return organizerPhone;
    }

    public void setOrganizerPhone(String organizerPhone) {
        this.organizerPhone = organizerPhone;
    }

}
