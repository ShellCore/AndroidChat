package com.edx.shell.android.androidchat.contactList;

import com.edx.shell.android.androidchat.contactList.events.ContactListEvent;

public interface ContactListPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    void signOff();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
    String getCurrentUserEmail();

}
