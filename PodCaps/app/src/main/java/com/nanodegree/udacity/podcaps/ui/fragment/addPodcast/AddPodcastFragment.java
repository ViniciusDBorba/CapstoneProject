package com.nanodegree.udacity.podcaps.ui.fragment.addPodcast;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.nanodegree.udacity.podcaps.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddPodcastFragment extends Fragment implements LifecycleOwner {


    private static final int SELECT_PODCAST = 2;
    private static final int SELECT_PODCAST_IMAGE = 3;
    @BindView(R.id.podcast_image)
    ImageView podcastImage;
    @BindView(R.id.podcast_name)
    EditText podcastName;
    @BindView(R.id.podcast_description)
    EditText podcastDescription;
    @BindView(R.id.podcast_file)
    EditText podcastFile;

    private AddPodcastPresenter presenter;

    Uri selectedImagePath;
    Uri selectedAudioPath;


    public AddPodcastFragment() {
        this.presenter = new AddPodcastPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_podcast, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        podcastFile.setKeyListener(null);
        podcastFile.setTag(new TextView(getContext()).getTag());
    }

    public void setEmptyError() {
        if (podcastName.getText() == null || podcastName.getText().toString().isEmpty()) {
            podcastName.setError(getResources().getString(R.string.podcast_empty_name));
        }
        if (selectedAudioPath == null) {
            podcastName.setError(getResources().getString(R.string.podcast_file_null));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null || data.getData() == null) {
            return;
        }

        switch (requestCode) {
            case SELECT_PODCAST:
                selectedAudioPath = data.getData();
                podcastFile.setText(selectedAudioPath.getLastPathSegment());
                break;
            case SELECT_PODCAST_IMAGE:

                selectedImagePath = data.getData();
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions
                        .centerInside()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);

                Glide.with(this)
                        .load(selectedImagePath)
                        .apply(requestOptions)
                        .into(podcastImage);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.podcast_image)
    public void selectImage() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        startActivityForResult(Intent.createChooser(i, getResources().getString(R.string.podcast_image_select)), SELECT_PODCAST_IMAGE);
    }

    @OnClick(R.id.podcast_file)
    public void selectFile() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("audio/*");

        startActivityForResult(Intent.createChooser(i, getResources().getString(R.string.podcast_file_select)), SELECT_PODCAST);
    }

    @OnClick(R.id.add_podcast)
    public void savePodcast() {
        presenter.savePodcast();

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null)
            fragmentManager.popBackStack();
    }
}
