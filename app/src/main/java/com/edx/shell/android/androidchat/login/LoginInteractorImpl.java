package com.edx.shell.android.androidchat.login;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl() {
        repository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        repository.checkSession();
    }

    @Override
    public void doSignin(String email, String pass) {
        repository.signin(email, pass);
    }

    @Override
    public void doSignup(String email, String pass) {
        repository.signup(email, pass);
    }
}
