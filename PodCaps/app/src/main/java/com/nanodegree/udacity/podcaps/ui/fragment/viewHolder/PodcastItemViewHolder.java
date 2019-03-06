package com.nanodegree.udacity.podcaps.ui.fragment.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.adapter.PodcastAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PodcastItemViewHolder extends BaseViewHolder<PodcastEntity> {

    @BindView(R.id.podcast_title)
    TextView podcastTitle;
    @BindView(R.id.podcast_description)
    TextView podcastDescription;
    @BindView(R.id.podcast_favorite_button)
    ImageView podcastFavoriteButton;

    private PodcastHolderListener listener;
    private PodcastEntity podcast;

    public PodcastItemViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setListener(PodcastHolderListener listener) {
        this.listener = listener;
    }

    public void bind(PodcastEntity podcastEntity) {
        this.podcast = podcastEntity;
        podcastTitle.setText(podcastEntity.getName());
        podcastDescription.setText(podcastEntity.getDescription());
    }

    @OnClick(R.id.podcast_wrapper)
    public void onClickPodcast() {
        listener.onClickPodcast(podcast);
    }

    @OnClick(R.id.podcast_favorite_button)
    public void onClickFavorite() {
        listener.onClickFavorite(podcast);
    }

    public interface PodcastHolderListener {
        void onClickPodcast(PodcastEntity podcast);

        void onClickFavorite(PodcastEntity podcast);
    }
}
