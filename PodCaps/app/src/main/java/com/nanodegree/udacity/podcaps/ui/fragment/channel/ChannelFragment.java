package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.udacity.podcaps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelFragment extends Fragment {

    @BindView(R.id.channel_podcast_list)
    RecyclerView myPodcastList;

    private ChannelPresenter presenter;

    public ChannelFragment() {
        presenter = new ChannelPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPodcastList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        myPodcastList.setAdapter(presenter.getAdapter());
    }
}
