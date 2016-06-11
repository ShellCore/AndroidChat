package com.edx.shell.android.androidchat.chat.ui;

import com.edx.shell.android.androidchat.entities.ChatMessage;

public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
