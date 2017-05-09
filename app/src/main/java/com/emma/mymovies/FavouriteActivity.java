package com.emma.mymovies;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.MovieAdapters.FavouriteAdapter;
import com.emma.mymovies.MovieAdapters.HomePageAdapter;
import com.emma.mymovies.MovieDataBase.MoviesContract;
import com.emma.mymovies.MovieUtils.SharedPrefUtils;
import com.emma.mymovies.MovieUtils.ToastMessageUtil;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private FavouriteAdapter adapter;
    private static final int LOADER_ID = 13;
    private RecyclerView fav_recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        //gets the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        //casting views
        fav_recyclerView = (RecyclerView) findViewById(R.id.favourite_rv);
        fav_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FavouriteAdapter(this);
        fav_recyclerView.setHasFixedSize(true);
        fav_recyclerView.setAdapter(adapter);


        //initialize the loader manager
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        //item touch helper that enables deleting item when swiped to the left or right

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //first we have to get the id from the tag we have set
                long id = (long)viewHolder.itemView.getTag();
                String Id = String.valueOf(id);

                //get the content uri to the whole SQLite table(content uri)
                Uri CONTENT_URI = MoviesContract.MovieTable.CONTENT_URI;

                //Append the id to the content uri to get the specific path to the row
                Uri mCONTENT_URI = CONTENT_URI.buildUpon().appendPath(Id).build();

                //send the path uri to the content provider for action
                getContentResolver().delete(mCONTENT_URI,null, null);

                //get the movie id form the array list of the data objects
                List<MovieModel> movieModels = HomePageAdapter.getMoviesModel();
                String movieId = (movieModels.get(viewHolder.getAdapterPosition()).getId()).toString();

                boolean IsPresent = SharedPrefUtils.isInFavourite(FavouriteActivity.this, movieId);

                if (IsPresent){
                    //also remove from favourite
                    SharedPrefUtils.removeFromFavourite(FavouriteActivity.this, movieId);
                }else {
                    ToastMessageUtil.getToastMessage(FavouriteActivity.this, "Cant Remove");
                }


                //re query for data
                getSupportLoaderManager().restartLoader(LOADER_ID, null, FavouriteActivity.this);
            }
        }).attachToRecyclerView(fav_recyclerView);//attach the item touch helper to the recycler view

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, FavouriteActivity.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new AsyncTaskLoader<Cursor>(this) {

            //reference to the cursor object
            Cursor c = null;

            @Override
            protected void onStartLoading() {
                super.onStartLoading();

                if (c != null){
                    deliverResult(c);//deliver the initial result
                }

                else {
                    forceLoad();//forces the stuff to load
                }
            }

            @Override
            public Cursor loadInBackground() {

                //query for data form the database through a contentResolver
                return getContentResolver().query(MoviesContract.MovieTable.CONTENT_URI,
                        null,
                        null,
                        null,
                        MoviesContract.MovieTable._ID);
            }

            @Override
            public void deliverResult(Cursor data) {
                super.deliverResult(data);
                c = data;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.SwapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.SwapCursor(null);//passes nothing when the loader is reset
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int Item = item.getItemId();//gets the clicked item

        if (Item == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);//navigates up
        }

        return true;
    }
}
