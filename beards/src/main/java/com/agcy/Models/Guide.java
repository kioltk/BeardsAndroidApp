package com.agcy.Models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.agcy.beards.core.Imager;

/**
 * Created by kiolt_000 on 14.02.14.
 */
public class Guide {
    public int id;
    public String title;
    public String description;
    public String type;
    public String content;
    public int imageId;
    public ImageView.ScaleType scaleType;

    public Drawable getImageDrawable(){
        return Imager.getDrawable(imageId);
    }
}
