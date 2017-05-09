package com.emma.mymovies.MovieUtils;

import android.content.Context;
import android.widget.ImageView;
import com.emma.mymovies.R;
import com.squareup.picasso.Picasso;


public class ImageLoaderUtils {

    //This loads images using picasso library
    public static void LoadImageIntoView(Context context, String imageUrl, ImageView imageView){

        Picasso.with(context)
                .load(imageUrl)
                .error(R.drawable.ic_art_track_grey_600_24dp)
                .into(imageView);
    }
}
