package com.edx.shell.android.androidchat.chat;

import com.edx.shell.android.androidchat.chat.events.ChatEvent;

public interface ChatPresenter {
    void onPost();
    void onResume();
    void onCreate();
    void onDestroy();

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
    void setChatRecipient(String recipient);
}
