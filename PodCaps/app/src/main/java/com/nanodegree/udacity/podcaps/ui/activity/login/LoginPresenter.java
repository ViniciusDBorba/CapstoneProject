package com.nanodegree.udacity.podcaps.ui.activity.login;

import com.nanodegree.udacity.podcaps.data.manager.UserManager;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;
import com.nanodegree.udacity.podcaps.ui.interfaces.BaseFirebaseService;

class LoginPresenter extends BaseFirebaseService implements UserManager.UserManagerListener {

    private final LoginActivity activity;
    private UserManager userManager;

    public LoginPresenter(LoginActivity loginActivity) {
        this.activity = loginActivity;
        this.userManager = new UserManager(this, activity);

        verifyLoggedUser();
    }

    private void verifyLoggedUser() {
        UserEntity user = userManager.getLoggedUser();

        if (user != null)
            activity.doLogin();

    }

    void login() {
        activity.setLoading(true);
        String text = this.activity.emailEdit.getText().toString();
        if (text == null || text.isEmpty()) {
            activity.emptyEmailError();
            activity.setLoading(false);
            return;
        }

        userManager.getUserByEmail(text);
    }

    @Override
    public void user(UserEntity user) {
        if (user == null) {
            userManager.newUser(this.activity.emailEdit.getText().toString());
        } else {
            user.setLogged(true);
            userManager.saveUser(user);
        }
        activity.doLogin();

    }

    @Override
    public void userSaved(UserEntity user) {
        // TODO: 24/01/2019 - implement loader to user save
    }

    @Override
    public void imageUploadProgress(double string) {

    }
}
