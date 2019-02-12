package com.nanodegree.udacity.podcaps.ui.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.channel.ChannelPresenter;
import com.nanodegree.udacity.podcaps.ui.fragment.viewHolder.ChannelPodcastItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChannelPodcastAdapter extends RecyclerView.Adapter<ChannelPodcastItemViewHolder> implements ChannelPodcastItemViewHolder.ChannelPodcastViewHolderListener {
    List<PodcastEntity> myPodcasts = new ArrayList<>();
    private ChannelPodcastItemViewHolder.ChannelPodcastViewHolderListener listener;

    @NonNull
    @Override
    public ChannelPodcastItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.podcast_channel_list_item, viewGroup, false);
        return new ChannelPodcastItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelPodcastItemViewHolder channelPodcastItemViewHolder, int i) {
        channelPodcastItemViewHolder.bind(getItem(i), this);
    }

    private PodcastEntity getItem(int i) {
        return myPodcasts.isEmpty() ? null : myPodcasts.get(i);
    }

    public void addPodcasts(List<PodcastEntity> podcasts) {
        myPodcasts.addAll(podcasts);
    }

    public void setListener(ChannelPodcastItemViewHolder.ChannelPodcastViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return myPodcasts.size();
    }

    @Override
    public void removePodcast(PodcastEntity podcastEntity) {
        listener.removePodcast(podcastEntity);
        myPodcasts.remove(podcastEntity);
        notifyDataSetChanged();
    }

    @Override
    public void selectPodcast(PodcastEntity podcast) {
        listener.selectPodcast(podcast);
    }


}
