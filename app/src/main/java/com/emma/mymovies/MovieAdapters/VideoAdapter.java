package com.emma.mymovies.MovieAdapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emma.mymovies.Models.VideoModel;
import com.emma.mymovies.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    private List<VideoModel> videoModels;
    private static final String BASE_MOVIE_URI = "https://www.youtube.com/watch?";
    private static final String QUERY_PARAMS = "v";

    public VideoAdapter(List<VideoModel> videoModels){
        this.videoModels = videoModels;

    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_item_view, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;

        public VideoViewHolder(View itemView) {
            super(itemView);

            //gets the image view
            imageView = (ImageView)itemView.findViewById(R.id.movie_thumbnail);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            //gets the adapter position
            int position = getAdapterPosition();

            //gets the youTube video Key
            String youTubeKey =  videoModels.get(position).getKey();

            //gets the youtube uri and converts it to string
            String youTubeUri = getUri(youTubeKey).toString();

            //opens up the browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youTubeUri));
            view.getContext().startActivity(browserIntent);
        }
    }


    private Uri getUri(String youTubeKey) {

        return Uri.parse(BASE_MOVIE_URI).buildUpon()
                .appendQueryParameter(QUERY_PARAMS, youTubeKey)
                .build();
    }
}
