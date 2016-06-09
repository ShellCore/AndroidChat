package com.edx.shell.android.androidchat.login;

public interface LoginView {
    void enableInputs();
    void diableInputs();
    void showProgress();
    void hideProgress();

    void handleSignin();
    void handleSignup();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
