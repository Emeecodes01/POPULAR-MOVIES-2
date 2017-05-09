package com.emma.mymovies.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emma.mymovies.Models.MovieModel;
import com.emma.mymovies.MovieAdapters.HomePageAdapter;
import com.emma.mymovies.R;

import java.util.List;

public class OverviewFragment extends Fragment {
    private List<MovieModel> movieModels;
    private TextView overView_tv;
    private RecyclerView review_rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_frag_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        overView_tv = (TextView)view.findViewById(R.id.overview);
        movieModels = HomePageAdapter.getMoviesModel();
        overView_tv.setText(movieModels.get(0).getOverview());
    }

}
