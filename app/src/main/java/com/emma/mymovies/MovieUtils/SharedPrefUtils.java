package com.emma.mymovies.MovieUtils;




import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;


public class SharedPrefUtils {
    public static final String PREF_FILE_NAME = "com.emma.mymovies";
    public static final String PREF_FAVOURITE_MOVIE = "favourite_movie";
    private static final int PREF_KEY_VALUE = 0;



    public static void AddToSharedPref(Context context, String movieId){
        SharedPreferences sharedPreferences = null;

        try {
            sharedPreferences = getSharedPreference(context);
            Set<String> stringSet = sharedPreferences.getStringSet(PREF_FAVOURITE_MOVIE, null);

            if (stringSet == null) stringSet = new HashSet<>();
            stringSet.add(movieId);

            //get the shared preference editor
            SharedPreferences.Editor editor = getPrefEditor(context);
            editor.clear();

            editor.putStringSet(PREF_FAVOURITE_MOVIE, stringSet);
            editor.apply();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static boolean isInFavourite(Context context, String movieId){
        SharedPreferences sharedPreferences;
        boolean isFavourite = true;

        try {
            sharedPreferences = getSharedPreference(context);
            Set<String> stringSet = sharedPreferences.getStringSet(PREF_FAVOURITE_MOVIE, null);
            if (stringSet == null){
                stringSet = new HashSet<>();
            }

            isFavourite = stringSet.contains(movieId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFavourite;
    }

    public static void removeFromFavourite(Context context, String movieId){
        SharedPreferences sharedPreferences = null;

        try {
            sharedPreferences = getSharedPreference(context);
            Set<String> stringSet = sharedPreferences.getStringSet(PREF_FAVOURITE_MOVIE, null);

            if (stringSet != null){

                if (stringSet.contains(movieId)){
                    stringSet.remove(movieId);

                    //get the shared preference editor
                    SharedPreferences.Editor editor = getPrefEditor(context);
                    editor.clear();//clear all data form the editor

                    editor.putStringSet(PREF_FAVOURITE_MOVIE, stringSet);
                    editor.apply();//apply all changes
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static SharedPreferences getSharedPreference(Context context) throws Exception{
        if (context == null){
            throw new Exception("context is null");
        }
        return context.getSharedPreferences(PREF_FILE_NAME, PREF_KEY_VALUE);
    }



    private static SharedPreferences.Editor getPrefEditor(Context context) throws Exception{
        if (context == null){
            throw new Exception("Context is null");
        }
        return getSharedPreference(context).edit();
    }
}