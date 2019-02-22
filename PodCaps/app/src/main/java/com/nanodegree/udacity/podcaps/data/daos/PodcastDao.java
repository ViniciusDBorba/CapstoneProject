package com.nanodegree.udacity.podcaps.data.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;

@Dao
public interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PodcastEntity podcast);

    @Query("SELECT * FROM podcasts")
    LiveData<PodcastEntity> getAll();


    @Query("SELECT * FROM podcasts WHERE podcasts.userEmail == :email")
    LiveData<PodcastEntity> getByEmail(String email);

    @Query("SELECT * FROM podcasts WHERE podcasts.selected == :selected")
    PodcastEntity getSelected(boolean selected);

    @Query("SELECT * FROM podcasts WHERE podcasts.selected == :selected")
    LiveData<PodcastEntity> getSelectedLive(boolean selected);

    @Delete()
    void remove(PodcastEntity podcast);

}
