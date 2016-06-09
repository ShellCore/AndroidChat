package com.edx.shell.android.androidchat.login;

public interface LoginRepository {
    void signin(String email, String pass);
    void signup(String email, String pass);
    void checkSession();
}
