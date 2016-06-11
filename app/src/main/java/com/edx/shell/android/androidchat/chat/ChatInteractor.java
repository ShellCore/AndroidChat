package com.edx.shell.android.androidchat.chat;

public interface ChatInteractor {
    void sendMessage();
    void setRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();
}
