package com.emma.mymovies;

import android.content.ContentValues;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.Models.MovieResponse;
import com.emma.mymovies.MovieAdapters.HomePageAdapter;
import com.emma.mymovies.MovieAdapters.SectionPagerAdapter;
import com.emma.mymovies.MovieDataBase.MoviesContract;
import com.emma.mymovies.MovieUtils.ImageLoaderUtils;
import com.emma.mymovies.MovieUtils.SharedPrefUtils;
import com.emma.mymovies.MovieUtils.ToastMessageUtil;
import com.emma.mymovies.Rest.ApiClient;
import com.emma.mymovies.Rest.ApiInterface;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;

public class DetailActitvity extends AppCompatActivity {
    private List<MovieModel> movieModels;
    private ImageView back_drop_img_view, poster_img_view;
    private TextView Title_tv, movieYear, movieLevel, rating;
    private SectionPagerAdapter sectionPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static int position;
    private ImageView fav_img_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actitvity);

        //casting all my views
        back_drop_img_view = (ImageView) findViewById(R.id.back_drop_img_view);
        poster_img_view = (ImageView) findViewById(R.id.movie_poster_img_view);
        Title_tv = (TextView) findViewById(R.id.movie_title);
        fav_img_view = (ImageView) findViewById(R.id.favourite_img);
        movieYear = (TextView) findViewById(R.id.release_date);
        rating = (TextView) findViewById(R.id.rating);
        movieLevel = (TextView) findViewById(R.id.movie_level);


        //sets up the tool bar as an action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }

        movieModels = HomePageAdapter.getMoviesModel();
         position = getIntent().getExtras().getInt("position");

        setUpViews(movieModels, position);

        sectionPagerAdapter= new SectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //set up the favorite image based on the favourite state
        if (SharedPrefUtils.isInFavourite(this, movieModels.get(position).getId().toString())){
            fav_img_view.setImageResource(R.drawable.ic_favorite_white_24dp);
        }

    }

    private void setUpViews(List<MovieModel> movieModels, int position) {
        //sets up the back drop image
        ImageLoaderUtils.LoadImageIntoView(getApplicationContext(), movieModels.get(position).getBackdropPath(),
                back_drop_img_view);
        //sets up the poster image
        ImageLoaderUtils.LoadImageIntoView(getApplicationContext(), movieModels.get(position).getPosterPath()
        , poster_img_view);
        //set up movie title
        Title_tv.setText(movieModels.get(position).getTitle());

        //set up the movie year
        movieYear.setText(movieModels.get(position).getReleaseDate());

        //set if the movie is adult or not
        boolean isAdult = movieModels.get(position).isAdult();
        if (isAdult){
            movieLevel.setText(getResources().getString(R.string.isAdult));
        }else {
            movieLevel.setText(getResources().getString(R.string.isNotAdult));
        }

        //setup the movie rating
        double movieRating = movieModels.get(position).getVoteAverage();
        rating.setText(String.valueOf(movieRating));


    }

    public static int getPosition(){
        return position;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddToFavourite(View view){
        //add to shared preferences
        addToSharedPref();

    }

    private void addToSharedPref() {
        //add to shared preference
        String movieId = movieModels.get(position).getId().toString();

        if (SharedPrefUtils.isInFavourite(this, movieId)){
            ToastMessageUtil.getToastMessage(this, "Is already in favourite");
        }

        else {

            //adds to database
            insertToDataBase();
            //puts in the favourite image
            changeImage();
            //adds to shared preference
            SharedPrefUtils.AddToSharedPref(this, movieId);
        }
    }

    private void insertToDataBase() {

        //get the name of the movie
        String movieName = movieModels.get(position).getTitle();
        //get the back drop path
        String backDropPath = movieModels.get(position).getBackdropPath();
        //get movie year
        String movieYear = movieModels.get(position).getReleaseDate();
        //gets rating
        String rating = String.valueOf(movieModels.get(position).getVoteAverage());
        //gets the reviews
        String movieReviews = movieModels.get(position).getOverview();

        ContentValues cv = new ContentValues();
        cv.put(MoviesContract.MovieTable.BACK_DROP_IMAGE_PATH, backDropPath);
        cv.put(MoviesContract.MovieTable.MOVIE_NAME, movieName);
        cv.put(MoviesContract.MovieTable.MOVIE_YEAR, movieYear);
        cv.put(MoviesContract.MovieTable.MOVIE_RATING, rating);
        cv.put(MoviesContract.MovieTable.MOVIE_REVIEWS, movieReviews);

        Uri Uri = getContentResolver().insert(MoviesContract.MovieTable.CONTENT_URI, cv);

        if (Uri != null){
            ToastMessageUtil.getToastMessage(getApplicationContext(), "Added to favorite Successfully");

        }

    }

    private void changeImage() {
        fav_img_view.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

}
