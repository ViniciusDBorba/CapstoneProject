package com.nanodegree.udacity.podcaps.utils;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseUtils {

    FirebaseFirestore db;

    public FireBaseUtils() {
        db = FirebaseFirestore.getInstance();
    }


    public CollectionReference getCollection(String user) {
        return db.collection(user);
    }
}
