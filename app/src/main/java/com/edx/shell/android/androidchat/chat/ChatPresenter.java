package com.edx.shell.android.androidchat.chat;

import com.edx.shell.android.androidchat.chat.events.ChatEvent;

public interface ChatPresenter {
    void onResume();
    void onCreate();
    void onPause();
    void onDestroy();

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
    void setChatRecipient(String recipient);

}
