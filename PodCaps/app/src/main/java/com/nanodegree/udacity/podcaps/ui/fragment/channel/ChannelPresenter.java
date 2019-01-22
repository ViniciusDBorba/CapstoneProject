package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.manager.UserManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.ChannelPodcastAdapter;

import java.util.List;

public class ChannelPresenter implements UserManager.UserManagerListener, PodcastManager.PodcastManagerListener {

    private final ChannelFragment fragment;
    private ChannelPodcastAdapter adapter;
    private PodcastManager podcastManager;
    private UserManager userManager;

    public ChannelPresenter(ChannelFragment fragment) {
        this.fragment = fragment;
        this.podcastManager = new PodcastManager(this, fragment.getContext());
        this.userManager = new UserManager(this, fragment.getContext());
    }

    public ChannelPodcastAdapter getAdapter() {
        this.adapter = new ChannelPodcastAdapter();
        userManager.getLoggedUser();
        return adapter;
    }


    @Override
    public void user(UserEntity user) {
        podcastManager.getPodcastsByEmail(user.getEmail());
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        this.adapter.addPodcasts(podcasts);
        this.adapter.notifyDataSetChanged();
    }
}
