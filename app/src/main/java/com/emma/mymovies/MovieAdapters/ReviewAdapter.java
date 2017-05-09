package com.emma.mymovies.MovieAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emma.mymovies.Models.ReviewsModel;
import com.emma.mymovies.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    List<ReviewsModel> reviewsModel;

    public ReviewAdapter(List<ReviewsModel> reviewsModel) {
        this.reviewsModel = reviewsModel;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View ReviewView  = inflater.inflate(R.layout.review_frag_item_view, parent, false);
        return new ReviewViewHolder(ReviewView);

    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

        holder.OnBindData(position);

    }

    @Override
    public int getItemCount() {
        return reviewsModel.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView reviewer_name, review;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            //cast views
            imageView = (ImageView)itemView.findViewById(R.id.reviewer_pix);
            reviewer_name = (TextView)itemView.findViewById(R.id.reviewer_name);
            review = (TextView)itemView.findViewById(R.id.tv_review);
        }

        void OnBindData(int position){
            reviewer_name.setText(reviewsModel.get(position).getAuthor());
            review.setText(reviewsModel.get(position).getContent());
        }
    }
}
