package com.edx.shell.android.androidchat.chat;

import com.edx.shell.android.androidchat.chat.events.ChatEvent;
import com.edx.shell.android.androidchat.chat.ui.ChatView;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class ChatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        chatInteractor = new ChatInteractorImpl();
        sessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null) {
            view.onMessageReceived(event.getMessage());
        }
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }
}
