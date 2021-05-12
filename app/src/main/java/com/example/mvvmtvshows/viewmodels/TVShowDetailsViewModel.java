package com.example.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtvshows.repositories.TVShowDetailRepository;
import com.example.mvvmtvshows.responses.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends ViewModel {
    private TVShowDetailRepository tvShowDetailRepository;

    public TVShowDetailsViewModel(){
        tvShowDetailRepository= new TVShowDetailRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        return tvShowDetailRepository.getTVShowDetails(tvShowId);
    }
}
