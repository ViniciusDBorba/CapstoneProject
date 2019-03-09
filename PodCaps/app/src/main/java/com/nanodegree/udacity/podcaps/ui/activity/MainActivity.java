package com.nanodegree.udacity.podcaps.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.ui.fragment.podcastFavoriteList.FavoritesPodcastList;
import com.nanodegree.udacity.podcaps.ui.fragment.channel.ChannelFragment;
import com.nanodegree.udacity.podcaps.ui.fragment.podcastList.PodcastListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LifecycleOwner {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.main_appbar)
    AppBarLayout appBar;

    @BindView(R.id.podcast_controls_toolbar_container)
    ConstraintLayout toolbarPodcastControllerLayout;

    @BindView(R.id.podcast_progress)
    ProgressBar podcastProgress;

    @BindView(R.id.podcast_control_next)
    ImageView podcastNext;

    @BindView(R.id.podcast_control_pause)
    ImageView podcastPlayPause;

    @BindView(R.id.podcast_control_pause_toolbar)
    ImageView podcastPlayPauseToolbar;

    @BindView(R.id.podcast_control_back)
    ImageView podcastBack;

    @BindView(R.id.podcast_name)
    TextView podcastName;


    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    toolbarPodcastControllerLayout.setVisibility(View.VISIBLE);

                } else if (isShow) {
                    isShow = false;
                    toolbarPodcastControllerLayout.setVisibility(View.GONE);
                }
            }
        });
        presenter = new MainPresenter(this);
        presenter.configPlayer();
        podcastProgress.setClickable(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        switchFragment(new PodcastListFragment(), true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyActivity();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_channel:
                switchFragment(new ChannelFragment(), true);
                return true;
            case R.id.navigation_podcast_list:
                switchFragment(new PodcastListFragment(), true);
                return true;
            case R.id.navigation_favorites:
                switchFragment(new FavoritesPodcastList(), true);
                return true;
        }
        return false;
    };

    @OnClick(R.id.podcast_control_pause)
    void playButton() {
        presenter.playPausePodcast();

    }
    @OnClick(R.id.podcast_control_pause_toolbar)
    void playButtonTollbar() {
        presenter.playPausePodcast();
    }

    @OnClick(R.id.podcast_control_next)
    void nextButton() {
        presenter.nextButtonPodcast();
    }

    @OnClick(R.id.podcast_control_next_toolbar)
    void nextButtonTollbar() {
        presenter.nextButtonPodcast();
    }

    @OnClick(R.id.podcast_control_back)
    void backButton() {
        presenter.backButtonPodcast();
    }

    @OnClick(R.id.podcast_control_back_toolbar)
    void backButtonTollbar() {
        presenter.backButtonPodcast();
    }

    public void updatePlayButton(boolean isPlaing) {
        podcastPlayPause.setImageDrawable(getResources().getDrawable(isPlaing ? R.drawable.ic_pause : R.drawable.ic_play));
        podcastPlayPauseToolbar.setImageDrawable(getResources().getDrawable(isPlaing ? R.drawable.ic_pause : R.drawable.ic_play));
    }

    public void setPodcastName(String name) {
        podcastName.setText(name);
    }

    public void setTotalPodcastTime(int duration) {
        podcastProgress.setMax(duration);
    }

    public void setPodcastProgress(int podcastProgress) {
        this.podcastProgress.setProgress(podcastProgress);
    }
}
