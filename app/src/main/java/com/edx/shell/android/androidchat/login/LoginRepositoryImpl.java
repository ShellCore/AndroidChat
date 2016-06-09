package com.edx.shell.android.androidchat.login;

import android.util.Log;

import com.edx.shell.android.androidchat.domain.FirebaseHelper;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.edx.shell.android.androidchat.login.events.LoginEvent;
import com.firebase.client.Firebase;

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signin(String email, String pass) {
        postEvent(LoginEvent.ON_SIGNIN_SUCCESS);
    }

    @Override
    public void signup(String email, String pass) {
        postEvent(LoginEvent.ON_SIGNUP_SUCCESS);
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.ON_FAILED_TO_RECOVER_SESSION);
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
