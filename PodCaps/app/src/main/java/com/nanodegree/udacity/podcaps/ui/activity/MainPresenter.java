package com.nanodegree.udacity.podcaps.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.media.MediaPlayer;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import java.io.IOException;
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
        } else {
            mediaPlayer.start();
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
