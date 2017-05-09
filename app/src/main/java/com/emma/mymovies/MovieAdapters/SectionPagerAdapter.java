package com.emma.mymovies.MovieAdapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.emma.mymovies.Fragments.PlaceholderFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter{

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        //set to have three pages
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Overview";
            case 1:
                return "Reviews";
            case 2:
                return "Thrillers";
        }
        return null;
    }
}
