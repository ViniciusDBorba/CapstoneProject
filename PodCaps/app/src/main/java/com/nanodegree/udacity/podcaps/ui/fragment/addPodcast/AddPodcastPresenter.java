package com.nanodegree.udacity.podcaps.ui.fragment.addPodcast;

import android.text.Editable;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.manager.PodcastManager;
import com.nanodegree.udacity.podcaps.data.manager.UserManager;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;

import java.util.List;
import java.util.UUID;

public class AddPodcastPresenter implements PodcastManager.PodcastManagerListener, UserManager.UserManagerListener {

    private AddPodcastFragment fragment;
    private PodcastEntity podcast;
    private PodcastManager manager;
    private UserManager userManager;

    AddPodcastPresenter(AddPodcastFragment fragment) {
        this.fragment = fragment;
        this.manager = new PodcastManager(this, fragment.getContext());
        this.userManager = new UserManager(this, fragment.getContext());
        this.podcast = new PodcastEntity(userManager.getLoggedUser());
    }

    void savePodcast() {
        if (fragment.selectedAudioPath == null) {
            fragment.setEmptyError();
            return;
        }
        Editable name = fragment.podcastName.getText();
        if (name == null || name.toString().isEmpty()) {
            fragment.setEmptyError();
            return;
        }

        podcast.setName(name.toString());
        Editable description = fragment.podcastDescription.getText();
        if (description != null || !description.toString().isEmpty())
            podcast.setDescription(description.toString());

        manager.savePodcast(podcast);

        String path = fragment.getResources().getString(R.string.podcast_file_storage_path, userManager.getLoggedUser().getEmail(), UUID.randomUUID());
        manager.uploadPodcastFile(fragment.selectedAudioPath, podcast, path);

        if (fragment.selectedImagePath != null) {
            path = fragment.getResources().getString(R.string.podcast_image_storage_path, userManager.getLoggedUser().getEmail(), UUID.randomUUID());
            manager.uploadPodcastImage(fragment.selectedImagePath, podcast, path);
        }

    }

    @Override
    public void podcasts(List<PodcastEntity> podcasts) {

    }

    @Override
    public void uploadPodcastProgress(int progress) {

    }

    @Override
    public void uploadPodcastImageProgress(int progress) {

    }

    @Override
    public void podcastSaved(PodcastEntity podcastEntity) {
        this.podcast = podcastEntity;
    }

    @Override
    public void user(UserEntity user) {

    }

    @Override
    public void userSaved(UserEntity user) {

    }

    @Override
    public void channelImageUploadProgress(double string) {

    }
}
