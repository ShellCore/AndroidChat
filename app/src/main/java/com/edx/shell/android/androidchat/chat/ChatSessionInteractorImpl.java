package com.edx.shell.android.androidchat.chat;

public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository repository;

    public ChatSessionInteractorImpl() {
        repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
