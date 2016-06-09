package com.edx.shell.android.androidchat;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    // CONSTANTES
    private static final String FIREBASE_URL = "https://shell-android-chat.firebaseio.com/";
    private static final String USERS_PATH = "users";
    private static final String CONTACTS_PATH = "contacts";
    private static final String CHATS_PATH = "chats";
    private static final String SEPARATOR = "___";

    // VARIABLES
    private Firebase dataReference;

    public FirebaseHelper() {
        dataReference = new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    /**
     * Función que devuelve el correo electrónico del usuario logueado.
     *
     * @return
     */
    public String getAuthUserEmail() {
        AuthData authData = dataReference.getAuth();
        String email = null;
        if (authData != null) {
            Map<String, Object> provider = authData.getProviderData();
            email = provider.get("email")
                    .toString();
        }
        return email;
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    /**
     * Accede a la referencia del usuario
     * @param email
     * @return
     */
    public Firebase getUserReference(String email) {
        Firebase reference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            reference = dataReference.getRoot()
                    .child(USERS_PATH)
                    .child(emailKey);
        }
        return reference;
    }

    /**
     * Accede a la referencia de usuario logueado
     * @return
     */
    public Firebase getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    /**
     * Obtiene todos los contactos de la referencia
     * @param email
     * @return
     */
    public Firebase getContactsReference(String email) {
        return getUserReference(email).child(CONTACTS_PATH);
    }

    /**
     * Obtiene todos los contactos del usuario logueado
     * @return
     */
    public Firebase getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    /**
     * Devuelve una referencia de acuerdo al contacto.
     * @param email
     * @param childEmail
     * @return
     */
    public Firebase getOneContactReference(String email, String childEmail) {
        String childKey = childEmail.replace(".", "_");
        return getUserReference(email).child(CONTACTS_PATH)
                .child(childKey);
    }

    /**
     * Función para obtener la referencia del chat del usuario
     * @param receiver
     * @return
     */
    public Firebase getChatReferences(String receiver) {
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }

        return dataReference.getRoot()
                .child(CHATS_PATH)
                .child(keyChat);
    }

    /**
     * Cambiar el estatus de la conexión de un usuario
     * @param online
     */
    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(online);
        }
    }

    /**
     * Notificar a los contactos que el estado ha sido cambiado
     * @param online
     */
    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    /**
     * Función para cerrar sesión
     */
    public void signoff() {
        notifyContactsOfConnectionChange(false, true);
    }

    /**
     * Función para notificar el estado del usuario y cierre de sesión
     * @param online
     * @param signoff
     */
    private void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyUserReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String email = child.getKey();
                    Firebase reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff) {
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
