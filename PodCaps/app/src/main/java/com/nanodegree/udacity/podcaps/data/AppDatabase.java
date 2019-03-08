package com.nanodegree.udacity.podcaps.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.nanodegree.udacity.podcaps.data.Converters.StringListConverter;
import com.nanodegree.udacity.podcaps.data.daos.PodcastDao;
import com.nanodegree.udacity.podcaps.data.daos.UserDao;
import com.nanodegree.udacity.podcaps.data.models.PodcastEntity;
import com.nanodegree.udacity.podcaps.data.models.UserEntity;

@Database(entities = {UserEntity.class, PodcastEntity.class}, version = 1, exportSchema = false)
@TypeConverters({StringListConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase instance;

    private static final String DATABASE_NAME = "PodCaps.db";

    public abstract UserDao userDao();
    public abstract PodcastDao podcastDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
