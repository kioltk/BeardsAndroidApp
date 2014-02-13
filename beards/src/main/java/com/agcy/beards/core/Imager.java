package com.agcy.beards.core;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.agcy.beards.R;

import java.util.Random;

/**
 * Created by kiolt_000 on 21.01.14.
 */
public class Imager {

    public static Context context;
    public static Drawable getRandomBeard(){
        Random random = new Random();
        int id = R.drawable.beard2;
        switch (random.nextInt(3)){
            case 0:
                id=R.drawable.beard1;
                break;
            case 1:
                id=R.drawable.beard2;
                break;
            case 2:
                id=R.drawable.beard3;
                break;
        }

        Drawable drawable = context.getResources().getDrawable(id);
        return drawable;
    }

    public static Drawable getBeard(int id){
        Drawable drawable = context.getResources().getDrawable(id);
        return drawable;
    }


}
