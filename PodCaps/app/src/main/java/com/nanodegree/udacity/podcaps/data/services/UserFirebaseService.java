package com.nanodegree.udacity.podcaps.data.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.interfaces.BaseFirebaseService;

public class UserFirebaseService extends BaseFirebaseService {

    private CollectionReference collection;

    public UserFirebaseService() {
        collection = firebase.getCollection("user");
    }


    public Task<QuerySnapshot> getUserByEmail(String text) {
        return collection.whereEqualTo("email", text).get();
    }

    public Task<DocumentReference> newUser(UserEntity user) {
        return collection.add(user);
    }
}
