package com.edx.shell.android.androidchat.contactList;

import com.edx.shell.android.androidchat.contactList.events.ContactListEvent;
import com.edx.shell.android.androidchat.domain.FirebaseHelper;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.EventBus;
import com.edx.shell.android.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import javax.sql.ConnectionEventListener;

public class ContactListRepositoryImpl implements ContactListRepository {

    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;
    private EventBus eventBus;

    public ContactListRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
        helper.signoff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email)
                .removeValue();
        helper.getOneContactReference(email, currentUserEmail)
                .removeValue();
    }

    @Override
    public void subscribeToContactListEvents() {

        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            };
        }
            helper.getMyContactsReference()
                    .addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace(".", "_");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User(email, online);
        post(type, user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);
    }

    @Override
    public void unsubscribeToContactListEvent() {
        if (contactEventListener != null) {
            helper.getMyContactsReference()
                    .removeEventListener(contactEventListener);
        }
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }
}
