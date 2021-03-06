package com.nanodegree.udacity.podcaps.ui.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.viewHolder.PodcastItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastItemViewHolder> implements PodcastItemViewHolder.PodcastHolderListener {

    List<PodcastEntity> podcasts = new ArrayList<>();
    private PodcastListListener listener;

    @NonNull
    @Override
    public PodcastItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.podcast_list_item, viewGroup, false);
        return new PodcastItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastItemViewHolder podcastItemViewHolder, int i) {
        podcastItemViewHolder.setListener(this);
        podcastItemViewHolder.bind(podcasts.get(i));
    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }

    public void setListener(PodcastListListener listener) {
        this.listener = listener;
    }

    public void addPodcasts(List<PodcastEntity> podcasts) {
        this.podcasts.addAll(podcasts);
    }

    @Override
    public void onClickPodcast(PodcastEntity podcast) {
        listener.onClickPodcast(podcast);
    }

    @Override
    public void onClickFavorite(PodcastEntity podcast) {
        listener.onClickFavorite(podcast);
    }

    @Override
    public String getLoggedUserEmail() {
        return listener.getLoggedUserEmail();
    }

    public void removePodcast(PodcastEntity podcast) {
        podcasts.remove(podcast);
    }

    public interface PodcastListListener {

        void onClickPodcast(PodcastEntity podcast);

        void onClickFavorite(PodcastEntity podcast);

        String getLoggedUserEmail();
    }
}
