package com.flowz.gads2020praticetask.models;

public class UserModel {

    public String email;
    public String firstname;
    public String lastname;
    public String link;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UserModel(String email, String firstname, String lastname, String link) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.link = link;
    }
}
