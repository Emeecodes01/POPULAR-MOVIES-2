package com.emma.mymovies.MovieAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.MovieUtils.ImageLoaderUtils;
import com.emma.mymovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MovieViewHolder>{

    private Context context;
    private static List<MovieModel> models;
    ListItemClickListener clickListener;

    public HomePageAdapter(Context c, List<MovieModel> movieModel, ListItemClickListener clickListener){
        this.models = movieModel;
        this.context = c;
        this.clickListener = clickListener;
    }

    public interface ListItemClickListener{
        void itemClicked(int ItemPosition);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View movie_view = inflater.inflate(R.layout.main_child_views_layout, parent, false);

        return new MovieViewHolder(movie_view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.OnBind(position);
    }


    @Override
    public int getItemCount() {

        return models.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView movie_imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            //casting my views
            movie_imageView = (ImageView)itemView.findViewById(R.id.main_image_view);

            itemView.setOnClickListener(this);
        }

        void OnBind(int position){
            String posterPath = models.get(position).getPosterPath();
            ImageLoaderUtils.LoadImageIntoView(context, posterPath, movie_imageView);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            clickListener.itemClicked(position);
        }
    }

    public static List<MovieModel> getMoviesModel(){
        return models;
    }
}
