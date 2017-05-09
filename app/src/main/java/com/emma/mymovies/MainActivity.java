package com.emma.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.Models.MovieResponse;
import com.emma.mymovies.MovieAdapters.HomePageAdapter;
import com.emma.mymovies.MovieUtils.ToastMessageUtil;
import com.emma.mymovies.Rest.ApiClient;
import com.emma.mymovies.Rest.ApiInterface;
import com.emma.mymovies.Rest.MovieApiKey;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements HomePageAdapter.ListItemClickListener {

    private static List<MovieModel> movielist;
    private RecyclerView main_rv;
    private HomePageAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cast all views
        main_rv = (RecyclerView)findViewById(R.id.main_recycler_view);
        main_rv.setLayoutManager(new GridLayoutManager(this, getSpansize()));
        main_rv.setHasFixedSize(true);

        getPopularMovie();
    }

    //THIS GETS THE POPULAR MOVIES VIA RETROFIT LIB
    private void getTopRatedMovie() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<MovieResponse> response = apiService.getTopRatedMovies(MovieApiKey.API_KEY);
        response.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, Response<MovieResponse> response) {
                movielist = response.body().getResults();
                HomePageAdapter adp = new HomePageAdapter(getApplicationContext(), movielist, MainActivity.this);
                main_rv.setAdapter(adp);
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                ToastMessageUtil.getToastMessage(getApplicationContext(), "Error");
            }
        });

    }

    //THIS GETS THE TOP RATED MOVIES VIA RETROFIT LIB
    private void getPopularMovie() {
        ApiInterface apiServie = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<MovieResponse> movieResponse = apiServie.getPopularMovies(MovieApiKey.API_KEY);
        movieResponse.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieModel>model = response.body().getResults();
                HomePageAdapter adp = new HomePageAdapter(getApplicationContext(),model, MainActivity.this);
                main_rv.setAdapter(adp);
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected){

            case R.id.sort_by_popularity:
                if (main_rv.getChildCount() != 0){//if there are items on the recycyler view
                    main_rv.removeAllViews();//remove all the views in it
                }
                getPopularMovie();

                break;
            case R.id.sort_by_top_rated:
                if (main_rv.getChildCount() != 0){
                    main_rv.removeAllViews();
                }
                getTopRatedMovie();

                break;
            case R.id.sort_by_favourite:

                //opens up the favorite activity
                Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private int getSpansize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int divider = 200;
        int TotalDisplayWidth = metrics.widthPixels;
        int nColumn = TotalDisplayWidth / divider;

        return (int) Math.ceil(nColumn);//round up to the nearest whole number
    }

    @Override
    public void itemClicked(int ItemPosition) {
        Intent intent = new Intent(MainActivity.this, DetailActitvity.class);
        intent.putExtra("position", ItemPosition);
        startActivity(intent);
    }
}