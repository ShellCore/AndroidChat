package com.edx.shell.android.androidchat.login;

import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.edx.shell.android.androidchat.login.events.LoginEvent;
import com.edx.shell.android.androidchat.login.ui.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
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

    @Override
    public void onEventMainThread(LoginEvent loginEvent) {
        switch (loginEvent.getEventType()) {
            case LoginEvent.ON_SIGNIN_SUCCESS:
                onSigninSuccess();
                break;
            case LoginEvent.ON_SIGNUP_SUCCESS:
                onSignupSuccess();
                break;
            case LoginEvent.ON_SIGNIN_ERROR:
                onSigninError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.ON_SIGNUP_ERROR:
                onSignupError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.ON_FAILED_TO_RECOVER_SESSION:
                onFailedToRecoverySession();
        }
    }

    private void onFailedToRecoverySession() {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
        }
    }

    private void onSigninSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignupSuccess() {
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
