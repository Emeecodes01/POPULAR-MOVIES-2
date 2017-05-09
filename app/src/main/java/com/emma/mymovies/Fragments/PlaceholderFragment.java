package com.emma.mymovies.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emma.mymovies.DetailActitvity;
import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.Models.ReviewResponse;
import com.emma.mymovies.Models.ReviewsModel;
import com.emma.mymovies.Models.VideoModel;
import com.emma.mymovies.Models.VideoResponse;
import com.emma.mymovies.MovieAdapters.HomePageAdapter;
import com.emma.mymovies.MovieAdapters.ReviewAdapter;
import com.emma.mymovies.MovieAdapters.VideoAdapter;
import com.emma.mymovies.MovieUtils.ToastMessageUtil;
import com.emma.mymovies.R;
import com.emma.mymovies.Rest.ApiClient;
import com.emma.mymovies.Rest.ApiInterface;
import com.emma.mymovies.Rest.MovieApiKey;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlaceholderFragment extends android.support.v4.app.Fragment {

    private RecyclerView reviews_rv;

    public PlaceholderFragment(){}

    public static PlaceholderFragment newInstance(int position){
        PlaceholderFragment phf = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        phf.setArguments(bundle);
        return phf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt("position");
        if (position == 0){
            View view = inflater.inflate(R.layout.overview_frag_layout, container, false);
            List<MovieModel> models = HomePageAdapter.getMoviesModel();
            setOverviewViews(models, DetailActitvity.getPosition(), view);
            return view;
        }
        else if (position == 1){
            View view = inflater.inflate(R.layout.reviews_frag_layout, container, false);
            List<MovieModel> model = HomePageAdapter.getMoviesModel();
            int movie_position = DetailActitvity.getPosition();
            int movieId = model.get(movie_position).getId();
            //use this id to make a network call using retrofit :):)
            getRetrofitNetworkAndSetViews(movieId, view);
            return view;
        }
        else if (position == 2){

            View view = inflater.inflate(R.layout.videos_frag_layout, container, false);

            List<MovieModel> models = HomePageAdapter.getMoviesModel();
            int postion = DetailActitvity.getPosition();
            int movieId = models.get(postion).getId();

            getVideoThroughRefrofitNetwork(movieId, view);
            return view;
        }
        return null;
    }

    private void getVideoThroughRefrofitNetwork(int movieId, final View view) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VideoResponse> videos = apiService.getVideos(movieId, MovieApiKey.API_KEY);
        videos.enqueue(new Callback<VideoResponse>() {


            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {

                //gets the movie model from the api response
                List<VideoModel> models = response.body().getVideoModels();
//
                //video adapter
                VideoAdapter Vadp = new VideoAdapter(models);

                //recycler view
                RecyclerView videoRv = (RecyclerView)view.findViewById(R.id.video_rv);
                videoRv.setHasFixedSize(true);
                videoRv.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
                videoRv.setAdapter(Vadp);

            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                ToastMessageUtil.getToastMessage(view.getContext(), "Cant get videos");
            }
        });
    }

    private void getRetrofitNetworkAndSetViews(int movieId, final View v) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReviewResponse> reviewResponse = apiService.getReviews(movieId, MovieApiKey.API_KEY);
        reviewResponse.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                List<ReviewsModel> reviews = response.body().getReviewsModels();
                ReviewAdapter adp = new ReviewAdapter(reviews);
                reviews_rv = (RecyclerView)v.findViewById(R.id.review_rv);
                reviews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                reviews_rv.setHasFixedSize(true);
                reviews_rv.setAdapter(adp);
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                String ErrorMessage = "Sorry Some thing went wrong:";
                ToastMessageUtil.getToastMessage(getActivity(), ErrorMessage);
                t.printStackTrace();
            }
        });
    }

    private void setOverviewViews(List<MovieModel> models, int position, View view) {
        TextView overview_tv = (TextView) view.findViewById(R.id.overview);
        overview_tv.setText(models.get(position).getOverview());
    }

}
