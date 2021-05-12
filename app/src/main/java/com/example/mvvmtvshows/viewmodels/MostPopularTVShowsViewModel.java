package com.example.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtvshows.repositories.MostPopularTVShowRepository;
import com.example.mvvmtvshows.responses.TVShowResponse;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowRepository mostPopularTVShowRepository;

    public MostPopularTVShowsViewModel(){
        mostPopularTVShowRepository= new MostPopularTVShowRepository();
    }

    public LiveData<TVShowResponse> getMostPopularTVShows(int page){
        return mostPopularTVShowRepository.getMostPopularTVShows(page);
    }
}
