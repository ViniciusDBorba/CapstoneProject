package com.nanodegree.udacity.podcaps.ui.activity.login;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.ui.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter();
    }

    @OnClick(R.id.enter_login_button)
    public void login(){
        if (presenter.login()) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
