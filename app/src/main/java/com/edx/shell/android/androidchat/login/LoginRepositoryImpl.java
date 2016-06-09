package com.edx.shell.android.androidchat.login;

import android.util.Log;

import com.edx.shell.android.androidchat.domain.FirebaseHelper;
import com.firebase.client.Firebase;

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signin(String email, String pass) {
        Log.d("LoginRepositoryImpl", "signin");
    }

    @Override
    public void signup(String email, String pass) {
        Log.d("LoginRepositoryImpl", "signup");
    }

    @Override
    public void checkSession() {
        Log.d("LoginRepositoryImpl", "checkSession");
    }
}
