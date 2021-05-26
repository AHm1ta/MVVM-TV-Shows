package com.example.mvvmtvshows.listener;

import com.example.mvvmtvshows.model.TVShow;

public interface WatchlistListener {

    void onTVShowClicked(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow, int position);
}
