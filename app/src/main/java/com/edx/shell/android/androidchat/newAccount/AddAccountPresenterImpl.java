package com.edx.shell.android.androidchat.newAccount;

import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.edx.shell.android.androidchat.newAccount.events.AddAccountEvent;
import com.edx.shell.android.androidchat.newAccount.ui.AddAccountView;

public class AddAccountPresenterImpl implements AddAccountPresenter {

    private EventBus eventBus;
    private AddAccountView view;
    private AddAccountInteractor interactor;

    public AddAccountPresenterImpl(AddAccountView view) {
        this.view = view;
        interactor = new AddAccountInteractorImpl();
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
    public void registerNewUser(String email, String pass) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.doSignUp(email, pass);
    }

    @Override
    public void onEventMainThread(AddAccountEvent addAccountEvent) {
        switch (addAccountEvent.getEventType()) {
            case AddAccountEvent.ON_SIGNIN_SUCCESS:
                onSigninSuccess();
                break;
            case AddAccountEvent.ON_SIGNUP_SUCCESS:
                onSignupSuccess();
                break;
            case AddAccountEvent.ON_SIGNIN_ERROR:
                onSigninError(addAccountEvent.getErrorMessage());
                break;
            case AddAccountEvent.ON_SIGNUP_ERROR:
                onSignupError(addAccountEvent.getErrorMessage());
                break;
            case AddAccountEvent.ON_FAILED_TO_RECOVER_SESSION:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onSigninSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignupSuccess() {
        if (view != null) {
            view.newUserSucess();
        }
    }

    private void onSigninError(String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.loginError(errorMessage);
        }
    }

    private void onSignupError(String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.newUserError(errorMessage);
        }
    }

    private void onFailedToRecoverSession() {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
        }
    }
}
