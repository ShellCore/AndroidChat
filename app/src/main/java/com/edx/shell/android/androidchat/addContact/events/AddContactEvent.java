package com.edx.shell.android.androidchat.addContact.events;

public class AddContactEvent {
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private boolean error = false;
}
