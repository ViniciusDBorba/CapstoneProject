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
        String text = this.activity.emailEdit.getText().toString();
        if (text == null || text.isEmpty())
            activity.emptyEmailError();

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
}
