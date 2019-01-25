package com.nanodegree.udacity.podcaps.ui.fragment.podcastList;

import android.support.v4.app.Fragment;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.PodcastAdapter;

import java.util.List;

public class PodcastListPresenter implements PodcastManager.PodcastManagerListener {

    private final PodcastListFragment fragment;
    private PodcastAdapter adapter;
    private PodcastManager manager;

    public PodcastListPresenter(PodcastListFragment fragment) {
        this.fragment = fragment;
        this.manager = new PodcastManager(this, fragment.getContext());
    }

    public PodcastAdapter getPodcastAdapter() {
        this.adapter = new PodcastAdapter();

        return adapter;
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {

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
