package com.nanodegree.udacity.podcaps.ui.fragment.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import butterknife.ButterKnife;

public class PodcastItemViewHolder extends BaseViewHolder<PodcastEntity> {

    public PodcastItemViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(PodcastEntity podcastEntity) {

    }
}
