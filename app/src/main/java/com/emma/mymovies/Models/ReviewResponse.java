package com.emma.mymovies.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("id")
    private int Id;
    @SerializedName("page")
    private int Page;
    @SerializedName("results")
    private List<ReviewsModel> reviewsModels;
    @SerializedName("total_pages")
    private int Pages;
    @SerializedName("total_results")
    private int totalResults;

    public ReviewResponse(int id, int page, List<ReviewsModel> reviewsModels, int pages, int totalResults) {
        Id = id;
        Page = page;
        this.reviewsModels = reviewsModels;
        Pages = pages;
        this.totalResults = totalResults;
    }

    public List<ReviewsModel> getReviewsModels() {
        return reviewsModels;
    }

    public int getId() {
        return Id;
    }

    public int getPage() {
        return Page;
    }

    public int getPages() {
        return Pages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
