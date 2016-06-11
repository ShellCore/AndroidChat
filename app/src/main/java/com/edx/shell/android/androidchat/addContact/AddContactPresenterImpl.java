package com.edx.shell.android.androidchat.addContact;

import com.edx.shell.android.androidchat.addContact.events.AddContactEvent;
import com.edx.shell.android.androidchat.addContact.ui.AddContactView;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class AddContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactInteractor interactor;
    private AddContactView view;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
