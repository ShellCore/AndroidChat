package com.edx.shell.android.androidchat.login;

import com.edx.shell.android.androidchat.login.events.LoginEvent;

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String pass);
    void registerNewUser(String email, String pass);
    void onEventMainThread(LoginEvent loginEvent);
}
