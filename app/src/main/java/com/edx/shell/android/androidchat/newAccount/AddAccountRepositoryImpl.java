package com.edx.shell.android.androidchat.newAccount;

import com.edx.shell.android.androidchat.domain.FirebaseHelper;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.edx.shell.android.androidchat.login.events.LoginEvent;
import com.edx.shell.android.androidchat.newAccount.events.AddAccountEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AddAccountRepositoryImpl implements AddAccountRepository {

    private Firebase dataReference;
    private Firebase myUserReference;

    private FirebaseHelper helper;

    public AddAccountRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        dataReference = helper.getDataReference();
        myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signup(final String email, final String pass) {
        dataReference.createUser(email, pass, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                postEvent(AddAccountEvent.ON_SIGNUP_SUCCESS);
                signin(email, pass);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(AddAccountEvent.ON_SIGNUP_ERROR, firebaseError.getMessage());
            }
        });
    }

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
                postEvent(AddAccountEvent.ON_SIGNIN_SUCCESS);
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
        AddAccountEvent event = new AddAccountEvent();
        event.setEventType(type);
        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
