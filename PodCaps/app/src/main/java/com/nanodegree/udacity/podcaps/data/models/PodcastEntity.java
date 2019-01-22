package com.nanodegree.udacity.podcaps.data.models;

import android.arch.persistence.room.Entity;

import java.time.LocalDateTime;

@Entity(tableName = "podcasts")
public class PodcastEntity {

    private String name;
    private String description;
    private String userEmail;
    private String timesPlayed;
    private String duration;
    private String url;
    private LocalDateTime uploadDate;

    public PodcastEntity() {
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

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
