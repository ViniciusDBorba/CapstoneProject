package com.nanodegree.udacity.podcaps.utils;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseUtils {

    private FirebaseFirestore db;
    private FirebaseStorage storage;

    public FireBaseUtils() {
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    private StorageReference getStorageReference() {
        return storage.getReference();
    }

    public CollectionReference getCollection(String user) {
        return db.collection(user);
    }

    public StorageReference getStorage(String path) {
        return getStorageReference().child(path);
    }
}
