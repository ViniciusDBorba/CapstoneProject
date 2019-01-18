package com.nanodegree.udacity.podcaps.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;


@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    private String email = "";
    private boolean logged;

    public UserEntity() {
    }

    @Ignore
    public UserEntity(String email) {
        this.email = email;
        logged = false;
    }

    @Ignore
    public UserEntity(DocumentSnapshot data) {
        this.email = data.getString("email");
        logged = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
