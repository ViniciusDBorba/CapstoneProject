package com.nanodegree.udacity.podcaps.data.services;

import android.net.Uri;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.interfaces.BaseFirebaseService;

public class PodcastFirebaseService extends BaseFirebaseService {

    private CollectionReference collection;

    public PodcastFirebaseService() {
        collection = firebase.getCollection("podcast");
    }

    public UploadTask uploadFile(Uri fileUri, String path) {
        final StorageReference storageRef = firebase.getStorage(path);
        return storageRef.putFile(fileUri);
    }

    public StorageReference getStorageRef(String path) {
        return firebase.getStorage(path);
    }

    public Task<Void> savePodcast(PodcastEntity podcastEntity) {
        return collection.document(String.valueOf(podcastEntity.getId())).set(podcastEntity);
    }

    public Task<QuerySnapshot> getFavoritesPodcastsByEmail(String email) {
        return collection.whereArrayContains("favoritedBy", email).get();
    }

    public Task<QuerySnapshot> getPodcastsByEmail(String email) {
        return collection.whereEqualTo("userEmail", email).get();
    }
    public Task<QuerySnapshot> getPodcasts() {
        return collection.get();
    }

    public void removePodcast(PodcastEntity podcastEntity) {
        collection.document(String.valueOf(podcastEntity.getId())).delete();
    }

}
