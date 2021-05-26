package com.example.mvvmtvshows.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmtvshows.dao.TVShowDao;
import com.example.mvvmtvshows.model.TVShow;

@Database(entities = TVShow.class, version = 1, exportSchema = false)
public abstract class TVShowDatabase extends RoomDatabase{

    private static TVShowDatabase tvShowsDatabase;

    public static synchronized TVShowDatabase getTvShowsDatabase(Context context) {
        if(tvShowsDatabase == null){
            tvShowsDatabase = Room.databaseBuilder(
                    context,
                    TVShowDatabase.class,
                    "tv_shows_db"
            ).build();
        }
        return tvShowsDatabase;
    }

    public abstract TVShowDao tvShowDao();

}