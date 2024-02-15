package com.example.whatsappclone.models;

import java.io.Serializable;

public class User implements Serializable {

    String Name,mobile,lastmsg;

    public User(String name, String mobile, String lastmsg) {
        Name = name;
        this.mobile = mobile;
        this.lastmsg = lastmsg;
    }

    public User(String name, String mobile) {
        Name = name;
        this.mobile = mobile;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastmsg() {
        return lastmsg;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }
}
