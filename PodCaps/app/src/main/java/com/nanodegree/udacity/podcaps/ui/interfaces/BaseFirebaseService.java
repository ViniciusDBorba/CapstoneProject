package com.nanodegree.udacity.podcaps.ui.interfaces;

import com.nanodegree.udacity.podcaps.utils.FireBaseUtils;

public class BaseFirebaseService {

    public FireBaseUtils firebase;

    public void BasePresenter() {
        firebase = new FireBaseUtils();
    }
}
