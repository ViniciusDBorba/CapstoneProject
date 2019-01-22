package com.nanodegree.udacity.podcaps.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

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

        switchFragment(new PodcastListFragment(), true);

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
