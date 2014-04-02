package com.agcy.beards.core;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.agcy.beards.Models.Guide;
import com.agcy.beards.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 22.01.14.
 */
public class Library {

    public static Context context;
    public static Guide getBeard(int id){

        Resources res = context.getResources();

        String name = res.getStringArray(R.array.beard_names)[id];
        String description = res.getStringArray(R.array.beard_descriptions)[id];
        String content = res.getStringArray(R.array.beard_contents)[id];
        int imageId = res.obtainTypedArray(R.array.beard_images).getResourceId(id,0);
        String scaleType = res.getStringArray(R.array.beard_scaletype)[id];
        return new Guide(id, name, description, content, "Growing", imageId, scaleType);
    }





    public static Guide getTip(int id) {

        return null;
    }

    public static ArrayList<Guide> getBeardsStyles() {

        ArrayList<Guide> beards = new ArrayList<Guide>();

        Resources res = context.getResources();

        String[] names = res.getStringArray(R.array.beard_names);
        String[] descriptions = res.getStringArray(R.array.beard_descriptions);
        String[] contents = res.getStringArray(R.array.beard_contents);
        TypedArray imageIds = res.obtainTypedArray(R.array.beard_images);
        String[] scaleType = res.getStringArray(R.array.beard_scaletype);

        for (int i = 0; i < names.length; i++) {
            beards.add(new Guide(i,names[i],descriptions[i],contents[i], "Growing", imageIds.getResourceId(i,0),scaleType[i]));
        }

        return beards;

    }

    public static ArrayList<Guide> getTips() {
        return null;
    }
}
