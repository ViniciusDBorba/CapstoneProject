package com.nanodegree.udacity.podcaps.ui.fragment.podcastFavoriteList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.udacity.podcaps.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesPodcastList extends Fragment {

    @BindView(R.id.podcast_list)
    RecyclerView favoritesPodcastList;
    @BindView(R.id.podcast_list_empty_text)
    TextView emptyText;

    private final FavoritesPodcastPresenter presenter;

    public FavoritesPodcastList() {
        this.presenter = new FavoritesPodcastPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_podcast_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoritesPodcastList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        favoritesPodcastList.setAdapter(presenter.getFavoritesPodcastAdapter());
        presenter.getPodcasts();
    }

    public void togglePodcastListEmptyText(boolean isVisible) {
        emptyText.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
