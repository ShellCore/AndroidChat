package com.edx.shell.android.androidchat.newAccount;

import com.edx.shell.android.androidchat.newAccount.events.AddAccountEvent;

public interface AddAccountPresenter {
    void onCreate();
    void onDestroy();
    void registerNewUser(String email, String pass);
    void onEventMainThread(AddAccountEvent addAccountEvent);
}
