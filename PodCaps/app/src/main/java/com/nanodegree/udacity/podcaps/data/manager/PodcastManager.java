package com.nanodegree.udacity.podcaps.data.manager;

import android.content.Context;

import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;

import java.util.List;

public class PodcastManager {
    private final PodcastManagerListener listener;

    public PodcastManager(PodcastManagerListener listener, Context context) {
        this.listener = listener;
    }

    public void getPodcastsByEmail(String email) {

    }


    public interface PodcastManagerListener {
        void podcasts(List<PodcastEntity> podcasts);
    }
}
