package com.edx.shell.android.androidchat.addContact;

import com.edx.shell.android.androidchat.addContact.events.AddContactEvent;

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
