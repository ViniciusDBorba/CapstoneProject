package com.nanodegree.udacity.podcaps.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nanodegree.udacity.podcaps.R;

public class BaseFragment extends Fragment {

    public void switchFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }
}
