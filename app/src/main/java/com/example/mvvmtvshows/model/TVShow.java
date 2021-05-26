package com.example.mvvmtvshows.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tvShows")
public class TVShow implements Serializable {

    @PrimaryKey
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }
}
