package com.nanodegree.udacity.podcaps.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.firestore.DocumentSnapshot;

@Entity(tableName = "podcasts")
public class PodcastEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String userEmail;
    private String timesPlayed;
    private String duration;
    private String url;
    private String uploadDate;
    private String imageUrl;

    public PodcastEntity() {
    }

    @Ignore
    public PodcastEntity(UserEntity loggedUser) {
        userEmail = loggedUser.getEmail();
    }

    @Ignore
    public PodcastEntity(DocumentSnapshot data) {
        id = (int) data.get("id");
        name = data.getString("name");
        description = data.getString("description");
        userEmail = data.getString("userEmail");
        duration = data.getString("duration");
        url = data.getString("url");
        uploadDate = data.getString("uploadDate");
        imageUrl = data.getString("imageUrl");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(String timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
