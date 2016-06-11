package com.edx.shell.android.androidchat.contactList.ui;

import com.edx.shell.android.androidchat.entities.User;

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
