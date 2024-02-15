package com.example.whatsappclone.models;

import com.google.firebase.auth.FirebaseAuth;

public class Message {
    String message,uId;
    long time;

    public Message(String message, String uId, long time) {
        this.message = message;
        this.uId = uId;
        this.time = time;
    }

    public Message(String message, String uId) {
        this.message = message;
        this.uId = uId;
    }

    public Message() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getuId() {
        return uId;
    }

    public long getTime() {
        return time;
    }
}
