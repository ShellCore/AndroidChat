package com.edx.shell.android.androidchat.contactList.ui.adapters;

import com.edx.shell.android.androidchat.entities.User;

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
