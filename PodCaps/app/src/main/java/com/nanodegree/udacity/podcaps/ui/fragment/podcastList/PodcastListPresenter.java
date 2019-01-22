package com.nanodegree.udacity.podcaps.ui.fragment.podcastList;

import android.support.v4.app.Fragment;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.PodcastAdapter;

public class PodcastListPresenter {

    private final PodcastListFragment fragment;
    private PodcastAdapter adapter;
    private PodcastManager manager;

    public PodcastListPresenter(PodcastListFragment fragment) {
        this.fragment = fragment;
        this.manager = new PodcastManager();
    }

    public PodcastAdapter getPodcastAdapter() {
        this.adapter = new PodcastAdapter();

        return adapter;
    }
}
