package com.nanodegree.udacity.podcaps.ui.activity;

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

import java.util.List;

public class MainPresenter implements PodcastManager.PodcastManagerListener {

    private MainActivity activity;
    private PodcastManager manager;
    private SimpleExoPlayer player;

    public MainPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
        this.manager = new PodcastManager(this, activity);
    }

    public void configPlayer() {
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(activity);
        activity.setPlayer(player);
        this.player = player;
        manager.getSelectedPodcast();
    }

    public void playPodcast() {

    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        if (podcasts.isEmpty())
            return;

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(activity,
                Util.getUserAgent(activity, "PodCaps"));
        Uri uri = new Uri.Builder().path(podcasts.get(0).getUrl()).build();
        MediaSource audioSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
        player.prepare(audioSource);
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
}
