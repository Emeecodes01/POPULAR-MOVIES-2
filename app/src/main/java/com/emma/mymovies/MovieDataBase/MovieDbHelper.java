package com.emma.mymovies.MovieDataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper {

    //name of the database
    private static final String MOVIE_DATABASE_NAME = "moviedata.db";

    //version number
    private static final int VERSION = 1;


    public MovieDbHelper(Context context) {
        super(context, MOVIE_DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_MOVIES_DATABASE_TABLE = "CREATE TABLE " + MoviesContract.MovieTable.TABLE_NAME + "( " +
                MoviesContract.MovieTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.MovieTable.BACK_DROP_IMAGE_PATH + " TEXT NOT NULL, " +
                MoviesContract.MovieTable.MOVIE_NAME + " TEXT NOT NULL, " +
                MoviesContract.MovieTable.MOVIE_YEAR + " TEXT NOT NULL, " +
                MoviesContract.MovieTable.MOVIE_RATING + " TEXT NOT NULL, " +
                MoviesContract.MovieTable.MOVIE_REVIEWS + " TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(CREATE_MOVIES_DATABASE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + MoviesContract.MovieTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
