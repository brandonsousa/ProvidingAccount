package br.com.seasyc.providingaccount.configs;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FStorage {
    private static StorageReference storageReference;

    public static StorageReference getStorage(String user) {
        if (storageReference == null) {
            return storageReference = FirebaseStorage.getInstance().getReference(user + "/");
        }
        return storageReference;
    }
}
