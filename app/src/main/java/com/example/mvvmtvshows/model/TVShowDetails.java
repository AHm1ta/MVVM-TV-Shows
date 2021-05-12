package com.example.mvvmtvshows.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowDetails {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("genres")
    @Expose
    private String[] genres;
    @SerializedName("pictures")
    @Expose
    private String[] pictures;
    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes;

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRating() {
        return rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getPictures() {
        return pictures;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
