package com.nanodegree.udacity.podcaps.ui.fragment.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelPodcastItemViewHolder extends BaseViewHolder<PodcastEntity> {

    @BindView(R.id.podcast_title)
    TextView podcastName;
    @BindView(R.id.podcast_title)
    TextView podcastDescription;

    public ChannelPodcastItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind (PodcastEntity podcastEntity) {
        podcastName.setText(podcastEntity.getName());
        podcastDescription.setText(podcastEntity.getDescription());
    }
}
