package com.edx.shell.android.androidchat.login;

public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String pass);
    void registerNewUser(String email, String pass);
}
