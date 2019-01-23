package com.nanodegree.udacity.podcaps.ui.fragment.channel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.fragment.AddPodcastFragment;
import com.nanodegree.udacity.podcaps.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelFragment extends BaseFragment {

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
            presenter.uploadChannelImage();
        }
    }

    @OnClick(R.id.add_podcast)
    public void addPodcast() {
        switchFragment(new AddPodcastFragment(), false);
    }

    public void updateView(UserEntity user) {
        String channelName = user.getChannelName();
        if (!channelName.isEmpty()) {
            channelTitle.setText(channelName);
        }

        String channelDescription = user.getChannelDescription();
        if (!channelDescription.isEmpty()) {
            channelDescriptionView.setText(channelDescription);
        }

        String channelImageUrl = user.getChannelImage();
        if (!channelImageUrl.isEmpty()) {
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

}
