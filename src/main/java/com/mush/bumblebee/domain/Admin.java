package com.mush.bumblebee.domain;

public class Admin {
    long id;
    String adminUniqueId;
    String adminFirstName;
    String getAdminLastName;
    String adminEmail;
    String adminPassword;

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }
}
