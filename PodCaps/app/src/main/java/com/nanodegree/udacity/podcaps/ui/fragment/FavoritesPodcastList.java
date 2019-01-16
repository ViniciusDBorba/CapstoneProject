package com.nanodegree.udacity.podcaps.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.udacity.podcaps.R;


public class FavoritesPodcastList extends Fragment {

    public FavoritesPodcastList() {
    }

    // TODO: Rename and change types and number of parameters
    public static FavoritesPodcastList newInstance(String param1, String param2) {
        FavoritesPodcastList fragment = new FavoritesPodcastList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_podcast_list, container, false);
    }
}
