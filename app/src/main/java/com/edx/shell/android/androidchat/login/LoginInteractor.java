package com.edx.shell.android.androidchat.login;

public interface LoginInteractor {
    void checkSession();
    void doSignin(String email, String pass);
    void doSignup(String email, String pass);
}
