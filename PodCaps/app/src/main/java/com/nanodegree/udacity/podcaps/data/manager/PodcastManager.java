package com.nanodegree.udacity.podcaps.data.manager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.nanodegree.udacity.podcaps.data.AppDatabase;
import com.nanodegree.udacity.podcaps.data.daos.PodcastDao;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.services.PodcastFirebaseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PodcastManager {
    private Context context;
    private PodcastManagerListener listener;
    private PodcastDao podcastDao;
    private PodcastFirebaseService firebaseService;


    public PodcastManager(PodcastManagerListener listener, Context context) {
        this.listener = listener;
        this.podcastDao = AppDatabase.getInstance(context).podcastDao();
        this.firebaseService = new PodcastFirebaseService();
        this.context = context;
    }

    public void getPodcastsByEmail(String email) {
        firebaseService.getPodcastsByEmail(email)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() == null || task.getResult().isEmpty()) {
                            listener.podcasts(null);
                            return;
                        }

                        List<DocumentSnapshot> docs = task.getResult().getDocuments();

                        if (!docs.isEmpty()) {
                            List<PodcastEntity> podcasts = new ArrayList<>();
                            for (DocumentSnapshot doc : docs) {
                                if (doc.exists()) {
                                    PodcastEntity podcastEntity = doc.getData() == null || doc.getData().isEmpty() ? null : new PodcastEntity(doc);
                                    podcasts.add(podcastEntity);
                                }
                            }
                            listener.podcasts(podcasts);
                            return;
                        }
                        listener.podcasts(null);
                    }
                });
    }

    public void savePodcast(final PodcastEntity podcastEntity) {
        if (podcastEntity.getUrl() != null && !podcastEntity.getUrl().isEmpty())
            firebaseService.savePodcast(podcastEntity).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        listener.podcastSaved(podcastEntity);
                    }
                }
            });
        podcastDao.insert(podcastEntity);
    }

    public void uploadPodcastFile(Uri selectedAudioPath, final PodcastEntity podcast, final String path) {
        UploadTask task = firebaseService.uploadFile(selectedAudioPath, path);
        task
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        firebaseService.getStorageRef(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                podcast.setUploadDate(new Date().toString());
                                podcast.setUrl(uri.toString());
                                savePodcast(podcast);
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        listener.uploadPodcastProgress((int) progress);
                    }
                });
    }

    public void uploadPodcastImage(Uri selectedImagePath, final PodcastEntity podcast, final String path) {
        UploadTask task = firebaseService.uploadFile(selectedImagePath, path);
        task
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        firebaseService.getStorageRef(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                podcast.setImageUrl(uri.toString());
                                savePodcast(podcast);
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        listener.uploadPodcastImageProgress((int) progress);
                    }
                });
    }


    public interface PodcastManagerListener {
        void podcasts(List<PodcastEntity> podcasts);

        void podcastSaved(PodcastEntity podcastEntity);

        void uploadPodcastProgress(int progress);

        void uploadPodcastImageProgress(int progress);
    }
}
