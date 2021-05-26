package com.example.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtvshows.repositories.MostPopularTVShowRepository;
import com.example.mvvmtvshows.repositories.SearchTVShowRepository;
import com.example.mvvmtvshows.responses.TVShowResponse;

public class SearchViewModel extends ViewModel {
    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel(){
        searchTVShowRepository= new SearchTVShowRepository();
    }

    public LiveData<TVShowResponse> searchTVShow(String query, int page){
        return searchTVShowRepository.searchTVShow(query,page);
    }
}
