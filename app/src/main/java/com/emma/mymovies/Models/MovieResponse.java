package com.emma.mymovies.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieResponse {
    @SerializedName("id")
    private int MovieId;
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MovieModel> results;
    @SerializedName("total_pages")
    private int totalResults;
    @SerializedName("total_results")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
