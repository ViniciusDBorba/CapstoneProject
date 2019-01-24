package com.nanodegree.udacity.podcaps.data.manager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.AppDatabase;
import com.nanodegree.udacity.podcaps.data.daos.UserDao;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.data.services.UserFirebaseService;

import java.util.List;
import java.util.UUID;

public class UserManager {
    private final UserManagerListener listener;
    private UserFirebaseService firebaseService;
    private UserDao userDao;

    public UserManager(UserManagerListener listener, Context context) {
        this.firebaseService = new UserFirebaseService();
        this.userDao = AppDatabase.getInstance(context).userDao();
        this.listener = listener;

    }


    public void getUserByEmail(String text) {
        firebaseService.getUserByEmail(text).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult() == null || task.getResult().isEmpty()) {
                    listener.user(null);
                    return;
                }

                List<DocumentSnapshot> docs = task.getResult().getDocuments();

                if (!docs.isEmpty()) {
                    DocumentSnapshot doc = docs.get(0);
                    if (doc.exists()) {
                        UserEntity user = doc.getData() == null || doc.getData().isEmpty() ? null : new UserEntity(doc);
                        listener.user(user);
                        return;
                    }
                }
                listener.user(null);
            }
        });
    }

    public void newUser(String email) {
        UserEntity user = new UserEntity(email);
        user.setLogged(true);
        saveUser(user);
    }

    public UserEntity getLoggedUser() {
        return userDao.getLoggedUser(true);
    }

    public void saveUser(final UserEntity user) {
        userDao.insert(user);
        Task<DocumentReference> task = firebaseService.saveUser(user);
        task.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    listener.userSaved(user);
                }
            }
        });

    }

    public void saveChannelImage(Uri imageUri, final Context context) {
        final UserEntity user = getLoggedUser();
        final String path = context.getResources().getString(R.string.channel_image_storage_path, user.getEmail(), UUID.randomUUID().toString());
        final UploadTask uploadTask = firebaseService.uploadChannelImage(imageUri, path);
        uploadTask
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        firebaseService.getStorageRef(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                user.setChannelImage(uri.toString());
                                saveUser(user);
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        listener.imageUploadProgress(progress);
                    }
                });
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                })

    }

    public interface UserManagerListener {
        void user(UserEntity user);

        void userSaved(UserEntity user);

        void imageUploadProgress(double string);
    }
}
