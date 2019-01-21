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

public class PodcastAdapter extends RecyclerView.Adapter<PodcastItemViewHolder> {

    List<PodcastEntity> podcasts = new ArrayList<>();

    @NonNull
    @Override
    public PodcastItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.podcast_list_item, viewGroup, false);
        return new PodcastItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastItemViewHolder podcastItemViewHolder, int i) {
        podcastItemViewHolder.bind(podcasts.get(i));
    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }
}
