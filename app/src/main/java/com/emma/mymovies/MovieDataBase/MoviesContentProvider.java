package com.emma.mymovies.MovieDataBase;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MoviesContentProvider extends ContentProvider{

    private MovieDbHelper movieDbHelper;

    //this matches with directory
    private static final int DIRECTORY = 100;

    //This matches with each row
    private static final int EACH_ROW = 101;

    //variable for the uri matcher
    public UriMatcher uriMatcher = getUriMatcher();

    private UriMatcher getUriMatcher() {
        UriMatcher match = new UriMatcher(UriMatcher.NO_MATCH);
        //matches with directory
        match.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH, DIRECTORY);
        //matches with single row of data
        match.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH + "/#", EACH_ROW);

        return match;
    }

    @Override
    public boolean onCreate() {

        //gets the context
        Context context = getContext();
        //initialise the db helper
        movieDbHelper = new MovieDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        //get a readable data base
        final SQLiteDatabase sdb = movieDbHelper.getReadableDatabase();

        //ref to a cursor object
        Cursor cursor;

        int MATCH = uriMatcher.match(uri);
        switch (MATCH){

            case DIRECTORY:
                cursor = sdb.query(MoviesContract.MovieTable.TABLE_NAME,
                        null, null, null, null, null, MoviesContract.MovieTable._ID);
                break;

            default:
                throw new UnsupportedOperationException("This operation is not supported");
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //gets a reference to a writable data base
        final SQLiteDatabase Db = movieDbHelper.getWritableDatabase();

        //reference to the returned uri
        Uri mUri;

        //matches to know which step to take
        int MATCH = uriMatcher.match(uri);
        switch (MATCH){
            case DIRECTORY:

                //insert the uri
                long Id = Db.insert(MoviesContract.MovieTable.TABLE_NAME, null, contentValues);

                if (Id > 0){
                    //insertion was successful
                    mUri = ContentUris.withAppendedId(uri, Id);
                }else {
                    throw new SQLException("cant insert :" + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Cant perform operation: " + uri);
        }

        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        //get the writable data base
        final SQLiteDatabase SDB = movieDbHelper.getWritableDatabase();

        int number_delected;

        //get the id from the uri passed in
        String id = (uri.getPathSegments().get(1));

        //define the selection
        String selection = "_id=?";

        //define the selection args
        String[] selection_args = {id};

        //uri matcher
        int MATCH = uriMatcher.match(uri);

        switch (MATCH){
            case EACH_ROW:
                number_delected = SDB.delete(MoviesContract.MovieTable.TABLE_NAME, selection, selection_args);
                break;
            default:
                throw new UnsupportedOperationException("This operation is unsupported");
        }

        if (number_delected != 0){
            //an item was delected
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return number_delected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
