package br.com.seasyc.providingaccount.services.FStorage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import br.com.seasyc.providingaccount.configs.FStorage;
import br.com.seasyc.providingaccount.util.QuicklyMessage;

public class Upload {

    public static void file(Uri uri, final Context context, String user, String name) {
        if (uri != null) {
            StorageReference fileReference = FStorage.getStorage(user).child(name
                    + "." + getExtension(uri, context));
            StorageTask storageTask = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            QuicklyMessage.toast(context, "Upload done");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            QuicklyMessage.toast(context, "Error on upload: " + e.getMessage());
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            QuicklyMessage.toast(context, progress + "%");
                        }
                    });
        } else {
            QuicklyMessage.toast(context, "Select Image");
        }
    }

    public static String getExtension(Uri uri, Context context) {
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
