package com.emma.mymovies.MovieDataBase;


import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    //the authority --> the package name
    public static final String AUTHORITY = "com.emma.mymovies";
    //base content uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    //table name which is the same as the path --> //BASE CONTENT URI <scheme>://<authority>
    public static final String PATH = "movie_table";

    private MoviesContract(){}


    public static class MovieTable implements BaseColumns{

        //NAME OF THE TABLE
        public static final String TABLE_NAME = "movie_table";

        //CONTENT URI --> <scheme>://<authority>/path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        //back drop image
        public static final String BACK_DROP_IMAGE_PATH = "movie_image_path";

        //movie name
        public static final String MOVIE_NAME = "movie_name";

        //movie year
        public static final String MOVIE_YEAR = "movie_year";

        //movie reviews
        public static final String MOVIE_REVIEWS = "reviews";

        //movie ratings
        public static final String MOVIE_RATING = "rating";

    }
}
