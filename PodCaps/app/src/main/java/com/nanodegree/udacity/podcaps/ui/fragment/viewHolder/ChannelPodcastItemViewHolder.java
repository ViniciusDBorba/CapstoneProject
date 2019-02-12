package com.nanodegree.udacity.podcaps.ui.fragment.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelPodcastItemViewHolder extends BaseViewHolder<PodcastEntity> {

    @BindView(R.id.podcast_title)
    TextView podcastName;
    @BindView(R.id.podcast_description)
    TextView podcastDescription;
    @BindView(R.id.podcast_remove_button)
    ImageView podcastRemoveBt;

    private PodcastEntity podcast;
    private ChannelPodcastViewHolderListener listener;

    public ChannelPodcastItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind (PodcastEntity podcastEntity, ChannelPodcastViewHolderListener listener) {
        this.podcast = podcastEntity;
        this.listener = listener;
        podcastName.setText(podcastEntity.getName());
        podcastDescription.setText(podcastEntity.getDescription());
    }

    @OnClick(R.id.podcast_remove_button)
    void removePodcast() {
        listener.removePodcast(podcast);
    }


    public interface ChannelPodcastViewHolderListener {
        void removePodcast(PodcastEntity podcastEntity);
    }
}
