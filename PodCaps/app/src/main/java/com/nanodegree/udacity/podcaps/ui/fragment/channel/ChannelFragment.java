package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.BaseFragment;
import com.nanodegree.udacity.podcaps.ui.fragment.addPodcast.AddPodcastFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelFragment extends BaseFragment implements LifecycleOwner {

    private static final int SELECT_IMAGE = 1;
    @BindView(R.id.channel_podcast_list)
    RecyclerView myPodcastList;
    @BindView(R.id.channel_title)
    TextView channelTitle;
    @BindView(R.id.channel_title_edit)
    EditText channelTitleEdit;
    @BindView(R.id.channel_description)
    TextView channelDescriptionView;
    @BindView(R.id.channel_description_edit)
    EditText channelDescriptionEdit;
    @BindView(R.id.channel_image)
    ImageView channelImage;
    @BindView(R.id.channel_edit)
    ImageView channelEditButton;
    @BindView(R.id.image_load)
    ProgressBar imageProgress;
    @BindView(R.id.loading_image_container)
    View imageContainer;
    @BindView(R.id.empty_podcast_list)
    TextView emptyList;

    private ChannelPresenter presenter;
    private boolean editing;

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
        presenter.getChannelData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || data.getData() == null)
            return;

        switch (requestCode) {
            case SELECT_IMAGE:
                presenter.uploadChannelImage(data.getData());
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.channel_edit)
    public void saveOrEnableChannelEdit() {
        editing = !editing;
        if (editing) {
            channelTitle.setVisibility(View.GONE);
            channelTitleEdit.setVisibility(View.VISIBLE);
            channelDescriptionView.setVisibility(View.GONE);
            channelDescriptionEdit.setVisibility(View.VISIBLE);
            channelEditButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_check));
        } else {
            presenter.saveChannelData();
            channelTitle.setVisibility(View.VISIBLE);
            channelTitleEdit.setVisibility(View.GONE);
            channelDescriptionView.setVisibility(View.VISIBLE);
            channelDescriptionEdit.setVisibility(View.GONE);
            channelEditButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_edit));
        }
    }

    @OnClick(R.id.channel_image)
    public void editChannelImage() {
        if (editing) {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("image/*");

            startActivityForResult(Intent.createChooser(i, getResources().getString(R.string.channel_image_select)), SELECT_IMAGE);
        }
    }

    @OnClick(R.id.add_podcast)
    public void addPodcast() {
        switchFragment(new AddPodcastFragment(), false);
    }

    public void updateView(UserEntity user) {
        String channelName = user.getChannelName();
        if (channelName != null && !channelName.isEmpty()) {
            channelTitle.setText(channelName);
        }

        String channelDescription = user.getChannelDescription();
        if (channelDescription != null && !channelDescription.isEmpty()) {
            channelDescriptionView.setText(channelDescription);
        }

        String channelImageUrl = user.getChannelImage();
        if (channelImageUrl != null && !channelImageUrl.isEmpty()) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(this)
                    .load(user.getChannelImage())
                    .apply(requestOptions)
                    .into(channelImage);
        }

    }

    public void updateImageProgress(double progress) {
        imageProgress.setProgress((int) progress);
    }

    public void togleImageUploadProgressBar(boolean isInit) {
        imageProgress.setVisibility(isInit ? View.VISIBLE : View.GONE);
        imageContainer.setVisibility(isInit ? View.VISIBLE : View.GONE);
    }

    public void togglePodcastListEmptyText(boolean visible) {
        emptyList.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
