package com.emma.mymovies.Models;


import android.support.v4.app.LoaderManager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {
    @SerializedName("id")
    private int movieId;
    @SerializedName("results")
    private List<VideoModel> videoModels;

    public VideoResponse(int movieId, List<VideoModel> videoModels) {
        this.movieId = movieId;
        this.videoModels = videoModels;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<VideoModel> getVideoModels() {
        return videoModels;
    }

    public void setVideoModels(List<VideoModel> videoModels) {
        this.videoModels = videoModels;
    }
}
