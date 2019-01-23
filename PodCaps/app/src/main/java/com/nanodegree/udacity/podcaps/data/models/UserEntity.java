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
    private String channelName;
    private String channelDescription;
    private String channelImage;

    public UserEntity() {
    }

    @Ignore
    public UserEntity(String email) {
        this.email = email;
        logged = false;
        channelName = "";
    }

    @Ignore
    public UserEntity(DocumentSnapshot data) {
        this.email = data.getString("email");
        logged = false;
        channelName = data.getString("channelName");
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getChannelImage() {
        return channelImage;
    }

    public void setChannelImage(String channelImage) {
        this.channelImage = channelImage;
    }
}
