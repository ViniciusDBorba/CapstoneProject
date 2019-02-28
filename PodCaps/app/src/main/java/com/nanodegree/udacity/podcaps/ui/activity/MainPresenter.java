package com.nanodegree.udacity.podcaps.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.media.MediaPlayer;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import java.util.List;

public class MainPresenter implements PodcastManager.PodcastManagerListener {

    private MainActivity activity;
    private PodcastManager manager;
    private MediaPlayer mediaPlayer;

    public MainPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
        this.manager = new PodcastManager(this, activity);
    }

    public void configPlayer() {
        this.mediaPlayer = new MediaPlayer();
        manager.getSelectedPodcast();
    }

    public void playPausePodcast() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            activity.updatePlayButton(false);
        } else {
            mediaPlayer.start();
            activity.updatePlayButton(true);
        }
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        if (podcasts.isEmpty())
            return;
        try {
            mediaPlayer.setDataSource(podcasts.get(0).getUrl());
            mediaPlayer.setVolume(100, 100);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void podcastSaved(PodcastEntity podcastEntity) {

    }

    @Override
    public void uploadPodcastProgress(int progress) {

    }

    @Override
    public void uploadPodcastImageProgress(int progress) {

    }

    @Override
    public LifecycleOwner getLifecycle() {
        return activity;
    }
}
