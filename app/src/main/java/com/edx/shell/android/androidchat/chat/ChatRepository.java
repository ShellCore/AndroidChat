package com.edx.shell.android.androidchat.chat;

public interface ChatRepository {
    void sendMessage();
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}