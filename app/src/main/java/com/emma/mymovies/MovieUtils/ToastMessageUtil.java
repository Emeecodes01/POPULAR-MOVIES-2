package com.emma.mymovies.MovieUtils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dell on 23/04/2017.
 */

public class ToastMessageUtil {

    public static void getToastMessage(Context context, String ToastMessage){
        Toast.makeText(context, ToastMessage, Toast.LENGTH_LONG).show();
    }
}
