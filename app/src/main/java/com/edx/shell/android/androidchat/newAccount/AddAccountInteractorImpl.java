package com.edx.shell.android.androidchat.newAccount;

public class AddAccountInteractorImpl implements AddAccountInteractor {

    private AddAccountRepository repository;

    public AddAccountInteractorImpl() {
        repository = new AddAccountRepositoryImpl();
    }

    @Override
    public void doSignUp(String email, String pass) {
        repository.signup(email, pass);
    }
}
