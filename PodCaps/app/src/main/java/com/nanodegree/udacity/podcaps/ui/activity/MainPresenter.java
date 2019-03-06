package com.nanodegree.udacity.podcaps.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainPresenter implements PodcastManager.PodcastManagerListener {

    private MainActivity activity;
    private PodcastManager manager;

    private MediaPlayer mediaPlayer;
    private int podcastProgress;
    private int duration;

    private Handler podcastProgressHandler = new Handler();
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            podcastProgress = mediaPlayer.getCurrentPosition();
            activity.setPodcastProgress(podcastProgress);
            podcastProgressHandler.postDelayed(this, 100);
        }
    };

    public MainPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
        this.manager = new PodcastManager(this, activity);
    }

    public void configPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.stop();
            activity.updatePlayButton(false);
        });
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

    public void nextButtonPodcast() {
        if ((podcastProgress + 500) <= duration) {
            podcastProgress = podcastProgress + 500;
            mediaPlayer.seekTo(podcastProgress);
        }
    }

    public void backButtonPodcast() {
        if ((podcastProgress - 500) >= 0) {
            podcastProgress = podcastProgress - 500;
            mediaPlayer.seekTo(podcastProgress);
        }
    }

    private void startPlayerProgress() {
        this.duration = mediaPlayer.getDuration();
        this.podcastProgress = mediaPlayer.getDuration();
        activity.setTotalPodcastTime(duration);
        activity.setPodcastProgress(podcastProgress);

        podcastProgressHandler.postDelayed(UpdateSongTime, 100);
    }

    public void onDestroyActivity() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        if (podcasts.isEmpty())
            return;
        try {
            PodcastEntity selectedPodcast = podcasts.get(0);
            mediaPlayer.setDataSource(selectedPodcast.getUrl());
            mediaPlayer.setOnPreparedListener(mp -> activity.setPodcastName(selectedPodcast.getName()));
            mediaPlayer.prepareAsync();

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
