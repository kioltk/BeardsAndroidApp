package com.agcy.Models;

import android.graphics.drawable.Drawable;

import com.agcy.beards.core.Imager;

/**
 * Created by kiolt_000 on 21.01.14.
 */
public class Beard {

    public int id;
    public String guide;
    public String title;
    public String description;
    public int imageId;

    public Drawable getImageDrawable(){
       return Imager.getBeard(imageId);
    }

    public Beard(){

    }

}
