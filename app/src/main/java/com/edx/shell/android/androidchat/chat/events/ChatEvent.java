package com.edx.shell.android.androidchat.chat.events;

import com.edx.shell.android.androidchat.entities.ChatMessage;

public class ChatEvent {

    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
