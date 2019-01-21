package com.nanodegree.udacity.podcaps.data.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nanodegree.udacity.podcaps.data.AppDatabase;
import com.nanodegree.udacity.podcaps.data.daos.UserDao;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.data.services.UserFirebaseService;

import java.util.List;

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
        firebaseService.newUser(user);
        user.setLogged(true);
        userDao.insert(user);
    }

    public UserEntity getLoggedUser() {
        return userDao.getLoggedUser(true);
    }

    public void saveUser(UserEntity user) {
        userDao.insert(user);
    }

    public interface UserManagerListener {
        void user(UserEntity user);
    }
}
