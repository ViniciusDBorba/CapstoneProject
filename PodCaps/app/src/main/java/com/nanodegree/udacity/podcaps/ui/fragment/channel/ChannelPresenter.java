package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import android.arch.lifecycle.LifecycleOwner;
import android.net.Uri;

import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.manager.UserManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.ChannelPodcastAdapter;
import com.nanodegree.udacity.podcaps.ui.fragment.viewHolder.ChannelPodcastItemViewHolder;

import java.util.List;

public class ChannelPresenter implements UserManager.UserManagerListener, PodcastManager.PodcastManagerListener, ChannelPodcastItemViewHolder.ChannelPodcastViewHolderListener {

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
        this.user = userManager.getLoggedUser();
        podcastManager.getPodcastsByEmail(user.getEmail());
        fragment.updateView(user);
    }

    public void saveChannelData() {
        String channelName = fragment.channelTitleEdit.getText().toString();
        String channelDescription = fragment.channelDescriptionEdit.getText().toString();

        if (!channelName.isEmpty())
            user.setChannelName(channelName);
        if (!channelDescription.isEmpty())
            user.setChannelDescription(channelDescription);

        fragment.updateView(user);
        userManager.saveUser(user);
    }

    void uploadChannelImage(Uri data) {
        fragment.togleImageUploadProgressBar(true);
        userManager.saveChannelImage(data, fragment.getContext());
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

        fragment.updateView(user);
    }

    @Override
    public void channelImageUploadProgress(double progress) {
        fragment.updateImageProgress(progress);
        if (progress >= 100)
            fragment.togleImageUploadProgressBar(false);
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
        return fragment;
    }

    @Override
    public void removePodcast(PodcastEntity podcastEntity) {
        podcastManager.remove(podcastEntity);
    }

    @Override
    public void selectPodcast(PodcastEntity podcast) {
        podcastManager.selectPodcast(podcast);
    }
}
