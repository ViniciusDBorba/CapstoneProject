package com.nanodegree.udacity.podcaps.ui.fragment.podcastList;

import android.arch.lifecycle.LifecycleOwner;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.PodcastAdapter;

import java.util.List;

public class PodcastListPresenter implements PodcastManager.PodcastManagerListener, PodcastAdapter.PodcastListListener {

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

    public void getPodcasts() {
        manager.getPodcasts();
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        if (podcasts == null || podcasts.isEmpty()) {
            fragment.togglePodcastListEmptyText(true);
            return;
        }
        fragment.togglePodcastListEmptyText(false);
        this.adapter.addPodcasts(podcasts);
        this.adapter.setListener(this);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickPodcast(PodcastEntity podcast) {
        manager.selectPodcast(podcast);
    }

    @Override
    public void onClickFavorite(PodcastEntity podcast) {
        // TODO: 06/03/2019 - implement favorites logic
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
        return fragment;
    }

}
