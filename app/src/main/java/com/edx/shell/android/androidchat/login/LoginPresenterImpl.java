package com.edx.shell.android.androidchat.login;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.checkSession();
    }

    @Override
    public void validateLogin(String email, String pass) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.doSignin(email, pass);
    }

    @Override
    public void registerNewUser(String email, String pass) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.doSignup(email, pass);
    }

    private void onSigninSucess() {
        if (view != null) {
            view.navigateToMainScreen();
        }

    }

    private void onSignupSucess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

    private void onSigninError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.loginError(error);
        }
    }

    private void onSignupError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.newUserError(error);
        }
    }
}
