package com.example.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUser {
    @SerializedName("chef")
    @Expose
     Chef chef;

    /**
     * No args constructor for use in serialization
     */
    public LoginUser() {
    }

    /**
     * @param chef
     */
    public LoginUser(Chef chef) {
        super();
        this.chef = chef;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

}
