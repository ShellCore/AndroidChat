package com.edx.shell.android.androidchat;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Map;
import java.util.Objects;

public class FirebaseHelper {

    // CONSTANTES
    private static final String FIREBASE_URL = "https://shell-android-chat.firebaseio.com/";
    private static final String USERS_PATH = "users";
    private static final String CONTACTS_PATH = "contacts";
    private static final String CHAT_PATH = "chats";
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
}
