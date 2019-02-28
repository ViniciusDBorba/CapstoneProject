package com.nanodegree.udacity.podcaps.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.media.MediaPlayer;
import android.widget.SeekBar;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
            startPlayerProgress();
            activity.updatePlayButton(true);
        }
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        if (podcasts.isEmpty())
            return;
        try {
            PodcastEntity selectedPodcast = podcasts.get(0);
            mediaPlayer.setDataSource(selectedPodcast.getUrl());
            mediaPlayer.setVolume(100, 100);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(mp -> {
                activity.setPodcastName(selectedPodcast.getName());
            });
            mediaPlayer.setOnCompletionListener(mp -> {
                activity.updatePlayButton(false);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPlayerProgress() {
        final int duration = mediaPlayer.getDuration();
        activity.podcastProgress.setMax(duration);
        final int amoungToupdate = duration / 100;
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(() -> {
                    if (!(amoungToupdate * activity.podcastProgress.getProgress() >= duration)) {
                        int p = activity.podcastProgress.getProgress();
                        p += 1;
                        activity.podcastProgress.setProgress(p);
                    }
                });
            }
        }, duration);
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
