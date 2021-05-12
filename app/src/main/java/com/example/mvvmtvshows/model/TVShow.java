package com.example.mvvmtvshows.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVShow {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image_thumbnail_path")
    @Expose
    private String imageThumbnailPath;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getStatus() {
        return status;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }
}
