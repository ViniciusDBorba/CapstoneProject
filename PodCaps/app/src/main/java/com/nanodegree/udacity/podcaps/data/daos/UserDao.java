package com.nanodegree.udacity.podcaps.data.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.nanodegree.udacity.podcaps.data.models.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Query("SELECT * FROM users WHERE users.logged == :logged")
    UserEntity getLoggedUser(boolean logged);

    @Delete()
    void remove(UserEntity user);
}
