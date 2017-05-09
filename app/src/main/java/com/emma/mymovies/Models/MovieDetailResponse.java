package com.emma.mymovies.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailResponse {

    @SerializedName("adult")
    private boolean adult;
    @SerializedName("backdrop_path")
    private String back_drop;
    @SerializedName("belongs_to_collection")
    private int collection;
    @SerializedName("budget")
    private int budget;
    @SerializedName("genres")
    private List<Genres> genresList;
    @SerializedName("homepage")
    private String homePage;
    @SerializedName("id")
    private String movieId;
    @SerializedName("imdb_id")
    private String imdb_Id;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("overview")
    private String OverView;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("postal_path")
    private String postal_path;
    @SerializedName("production_companies")
    private List<ProductionCompanies> companiesList;
    @SerializedName("production_countries")
    private List<ProductionCountries> productionCountriesList;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("revenue")
    private Double revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> languageList;
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private Double vote_average;
    @SerializedName("vote_count")
    private String vote_count;

}
