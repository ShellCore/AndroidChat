package com.edx.shell.android.androidchat.addContact;

import com.edx.shell.android.androidchat.addContact.events.AddContactEvent;
import com.edx.shell.android.androidchat.addContact.ui.AddContactView;

public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}
