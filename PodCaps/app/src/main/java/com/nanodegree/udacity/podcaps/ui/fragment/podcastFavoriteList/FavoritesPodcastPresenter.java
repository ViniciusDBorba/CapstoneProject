package com.nanodegree.udacity.podcaps.ui.fragment.podcastFavoriteList;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.manager.UserManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.PodcastAdapter;

import java.util.List;

public class FavoritesPodcastPresenter implements PodcastManager.PodcastManagerListener, PodcastAdapter.PodcastListListener {

    private final FavoritesPodcastList fragment;
    private PodcastManager manager;
    private UserManager userManager;
    private PodcastAdapter adapter;

    public FavoritesPodcastPresenter(FavoritesPodcastList favoritesPodcastList) {
        this.fragment = favoritesPodcastList;
        manager = new PodcastManager(this, fragment.getContext());
        userManager = new UserManager(fragment.getContext());
    }

    public PodcastAdapter getFavoritesPodcastAdapter() {
        this.adapter = new PodcastAdapter();
        adapter.setListener(this);
        return adapter;
    }

    public void getPodcasts() {
        manager.getFavoritesPodcastsByEmail(getLoggedUserEmail());
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
        return null;
    }

    @Override
    public void onClickPodcast(PodcastEntity podcast) {
        manager.selectPodcast(podcast);
    }

    @Override
    public void onClickFavorite(PodcastEntity podcast) {
        manager.favoritePodcast(podcast, getLoggedUserEmail());
        adapter.removePodcast(podcast);
        adapter.notifyDataSetChanged();

    }

    @Override
    public String getLoggedUserEmail() {
        return userManager.getLoggedUser().getEmail();
    }
}
