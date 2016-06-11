package com.edx.shell.android.androidchat.entities;

import java.util.Map;

public class User {

    public static final boolean ONLINE = true;
    public static final boolean OFFLINE = false;

    private String email;
    private boolean online;
    Map<String, Boolean> contacts;

    public User() {
    }

    public User(String email, boolean online) {
        this.email = email;
        this.online = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof User) {
            User user = (User) o;
            equal = email.equals(user.getEmail());
        }

        return equal;
    }
}
