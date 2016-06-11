package com.edx.shell.android.androidchat.contactList;

public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void subscribeToContactListEvents();
    void unsubscribeToContactListEvent();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}
