package com.edx.shell.android.androidchat.newAccount.ui;

public interface AddAccountView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void newUserSucess();
    void newUserError(String error);

    void navigateToMainScreen();
    void loginError(String errorMessage);
}
