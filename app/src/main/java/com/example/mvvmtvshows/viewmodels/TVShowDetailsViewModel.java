package com.example.mvvmtvshows.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtvshows.database.TVShowDatabase;
import com.example.mvvmtvshows.model.TVShow;
import com.example.mvvmtvshows.repositories.TVShowDetailRepository;
import com.example.mvvmtvshows.responses.TVShowDetailsResponse;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TVShowDetailsViewModel extends AndroidViewModel {

    private TVShowDetailRepository tvShowDetailRepository;
    private TVShowDatabase tvShowDatabase;

    public TVShowDetailsViewModel(@NonNull Application application){
        super(application);
        tvShowDetailRepository= new TVShowDetailRepository();
        tvShowDatabase= TVShowDatabase.getTvShowsDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        return tvShowDetailRepository.getTVShowDetails(tvShowId);
    }

    public Completable addToWatchlist(TVShow tvShow){
        return tvShowDatabase.tvShowDao().addToWatchlist(tvShow);
    }
    public Flowable<TVShow> getTVShowFromWatchlist(String tvShowId){
        return tvShowDatabase.tvShowDao().getTvShowFromWatchlist(tvShowId);
    }
    public Completable removeTVShowFromWatchlist(TVShow tvShow){
        return tvShowDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }
}
