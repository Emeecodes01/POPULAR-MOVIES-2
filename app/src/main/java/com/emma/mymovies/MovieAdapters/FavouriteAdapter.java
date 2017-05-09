package com.emma.mymovies.MovieAdapters;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emma.mymovies.MovieDataBase.MoviesContract;
import com.emma.mymovies.MovieUtils.ImageLoaderUtils;
import com.emma.mymovies.MovieUtils.ToastMessageUtil;
import com.emma.mymovies.R;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>{

    private Cursor mcursor;
    private Context context;

    public FavouriteAdapter(Context context){
        this.context = context;
    }


    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourite_layout, parent, false);

        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        String title, year,img_uri;

        if (mcursor.moveToPosition(position)){

            //gets the title of the movie
            title = mcursor.getString(mcursor.getColumnIndex(MoviesContract.MovieTable.MOVIE_NAME));
            //gets the id of the current position
            long id = mcursor.getLong(mcursor.getColumnIndex(MoviesContract.MovieTable._ID));
            //gets the year of the movie
            year = mcursor.getString(mcursor.getColumnIndex(MoviesContract.MovieTable.MOVIE_YEAR));
            //get the image uri
            img_uri = mcursor.getString(mcursor.getColumnIndex(MoviesContract.MovieTable.BACK_DROP_IMAGE_PATH));

            //gets the tag
            holder.itemView.setTag(id);
            //sets up the views
            holder.OnBindViews(title, year, img_uri);
        }
    }

    @Override
    public int getItemCount() {

        if (mcursor == null){
//            ToastMessageUtil.getToastMessage(context, "You do not have any favourite movie");
            return 0;
        }
        return mcursor.getCount();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder{
        private TextView movie_title, movie_year;
        private ImageView imageView;


        public FavouriteViewHolder(View itemView) {
            super(itemView);

            //casting the view that was inflated
            movie_title = (TextView)itemView.findViewById(R.id.movie_title2);
            movie_year = (TextView)itemView.findViewById(R.id.movie_year);
            imageView = (ImageView) itemView.findViewById(R.id.backdrop_img);

        }

        void OnBindViews(String title, String year, String img_uri){
            //sets up the movie title
            movie_title.setText(title);
            //sets up the movie year
            movie_year.setText(year);
            //sets up the back drop image
            ImageLoaderUtils.LoadImageIntoView(itemView.getContext(), img_uri, imageView);

        }

    }

    public Cursor SwapCursor(Cursor cursor){
        //check if the two cursor are the same
        if (mcursor == cursor){
            return null;
        }

        Cursor temp = mcursor;
        this.mcursor = cursor;//assigns the new cursor

        if (cursor != null){
            this.notifyDataSetChanged();
        }

        return temp;
    }
}