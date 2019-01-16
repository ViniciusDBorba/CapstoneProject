package com.nanodegree.udacity.podcaps.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.udacity.podcaps.R;


public class PodcastListFragment extends Fragment {

    private String mParam1;
    private String mParam2;

    public PodcastListFragment() {
    }


    public static PodcastListFragment newInstance(String param1, String param2) {
        PodcastListFragment fragment = new PodcastListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_podcast_list, container, false);
    }



}
