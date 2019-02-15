package com.nanodegree.udacity.podcaps.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.ui.fragment.channel.ChannelFragment;
import com.nanodegree.udacity.podcaps.ui.fragment.FavoritesPodcastList;
import com.nanodegree.udacity.podcaps.ui.fragment.podcastList.PodcastListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.main_appbar)
    AppBarLayout appBar;

    @BindView(R.id.podcast_controls_toolbar_container)
    ConstraintLayout toolbarPodcastControllerLayout;

    @BindView(R.id.podcast_control_progress)
    SeekBar podcastProgress;

    @BindView(R.id.podcast_control_next)
    ImageView podcastNext;

    @BindView(R.id.podcast_control_pause)
    ImageView podcastPause;

    @BindView(R.id.podcast_control_back)
    ImageView podcastBack;

    @BindView(R.id.player_view)
    PlayerView playerView;


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

        switchFragment(new PodcastListFragment(), true);

    }

    public void setPlayer(SimpleExoPlayer player) {
        playerView.setPlayer(player);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_channel:
                    switchFragment(new ChannelFragment(),true);
                    return true;
                case R.id.navigation_podcast_list:
                    switchFragment(new PodcastListFragment(), true);
                    return true;
                case R.id.navigation_favorites:
                    switchFragment(new FavoritesPodcastList(), true);
                    return true;
            }
            return false;
        }
    };

}
