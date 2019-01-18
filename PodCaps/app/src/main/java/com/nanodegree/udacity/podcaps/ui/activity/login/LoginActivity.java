package com.nanodegree.udacity.podcaps.ui.activity.login;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.nanodegree.udacity.podcaps.R;
import com.nanodegree.udacity.podcaps.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;
    @BindView(R.id.login_email_edit)
    EditText emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.enter_login_button)
    public void login() {
        presenter.login();
    }

    public void doLogin() {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
    }

    public void emptyEmailError() {
        emailEdit.setError("O email não pode ser vazio");
    }
}
