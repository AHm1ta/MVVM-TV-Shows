package com.example.mvvmtvshows.responses;

import com.example.mvvmtvshows.model.TVShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowResponse {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pages")
    @Expose
    private Integer totalPages;
    @SerializedName("tv_shows")
    @Expose
    private List<TVShow> tvShows;

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }
}
