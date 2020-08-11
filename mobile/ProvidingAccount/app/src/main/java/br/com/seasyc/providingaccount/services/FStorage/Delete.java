package br.com.seasyc.providingaccount.services.FStorage;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import br.com.seasyc.providingaccount.configs.FStorage;
import br.com.seasyc.providingaccount.util.QuicklyMessage;

public class Delete {
    private FirebaseStorage storage;

    public static void file(final Context context, String name) {
        StorageReference fileReference = FStorage.getStorage().child(name);
        fileReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                QuicklyMessage.toast(context, "Imagem deletada");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                QuicklyMessage.toast(context, e.getMessage());
            }
        });
    }
}
