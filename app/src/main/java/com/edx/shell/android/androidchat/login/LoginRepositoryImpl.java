package com.edx.shell.android.androidchat.login;

import com.edx.shell.android.androidchat.domain.FirebaseHelper;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.edx.shell.android.androidchat.login.events.LoginEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class LoginRepositoryImpl implements LoginRepository {

    private Firebase dataReference;
    private Firebase myUserReference;

    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        dataReference = helper.getDataReference();
        myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signin(String email, String pass) {
        dataReference.authWithPassword(email, pass, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                initSignin();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.ON_SIGNIN_ERROR, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void signup(final String email, final String pass) {
        dataReference.createUser(email, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.ON_SIGNUP_SUCCESS);
                signin(email, pass);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.ON_SIGNUP_ERROR, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        if (dataReference.getAuth() != null) {
            initSignin();
        } else {
            postEvent(LoginEvent.ON_FAILED_TO_RECOVER_SESSION);
        }
    }

    private void initSignin() {
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                if (currentUser == null) {
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.ON_SIGNIN_SUCCESS);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
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
