package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import android.text.Editable;

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
    private UserEntity user;

    public ChannelPresenter(ChannelFragment fragment) {
        this.fragment = fragment;
        this.podcastManager = new PodcastManager(this, fragment.getContext());
        this.userManager = new UserManager(this, fragment.getContext());
    }

    public ChannelPodcastAdapter getAdapter() {
        this.adapter = new ChannelPodcastAdapter();
        return adapter;
    }

    public void getChannelData() {
        userManager.getLoggedUser();
    }

    public void saveChannelData() {
        String channelName = fragment.channelTitleEdit.getText().toString();
        String channelDescription = fragment.channelDescriptionEdit.getText().toString();
        user.setChannelName(channelName);
        user.setChannelDescription(fragment.channelDescriptionEdit.getText().toString());
        fragment.updateView(user);
        userManager.saveUser(user);
    }

    @Override
    public void user(UserEntity user) {
        this.user = user;
        podcastManager.getPodcastsByEmail(user.getEmail());
        fragment.updateView(user);
    }

    @Override
    public void userSaved(UserEntity user) {
        // TODO: 23/01/2019 - implement a loading to show when user is saved
    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {
        this.adapter.addPodcasts(podcasts);
        this.adapter.notifyDataSetChanged();
    }

    public void uploadChannelImage() {
        // TODO: 23/01/2019 implement channel image upload
    }
}
