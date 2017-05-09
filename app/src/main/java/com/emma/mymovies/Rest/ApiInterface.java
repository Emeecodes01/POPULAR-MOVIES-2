package com.emma.mymovies.Rest;

import com.emma.mymovies.Models.MovieDetailResponse;
import com.emma.mymovies.Models.MovieResponse;
import com.emma.mymovies.Models.ReviewResponse;
import com.emma.mymovies.Models.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //get the top rated movies
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    //gets the popular movies
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    //get the movie details
    @GET("movie/{id}")
    Call<MovieDetailResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    //get the movie reviews
    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") Integer id, @Query("api_key") String apiKey);

    //get the videos
    @GET("movie/{id}/videos")
    Call<VideoResponse> getVideos(@Path("id") Integer id, @Query("api_key") String apiKey);

}
