package com.tanka.iot.pojo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("url")
    String url;

    @SerializedName("username")
    String username;

    @SerializedName("email")
    String email;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
