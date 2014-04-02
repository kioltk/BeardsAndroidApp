package com.agcy.beards.Models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.agcy.beards.core.Imager;

/**
 * Created by kiolt_000 on 14.02.14.
 */
public class Guide {
    public final int id;
    public final String title;
    public final String description;
    public final String type;
    public String content;
    public int imageId;
    public ImageView.ScaleType scaleType;

    public Drawable getImageDrawable(){
        return Imager.getDrawable(imageId);
    }

    public Guide(int id, String name, String description, String content, String type, int imageId, String scaleType){
        this.id = id;
        this.type = type;
        this.title = name;
        this.description = description;
        this.content = content;
        this.imageId = imageId;
        if(!scaleType.equalsIgnoreCase("none")){
            do {
                if(scaleType.equalsIgnoreCase("center")) {
                    this.scaleType = ImageView.ScaleType.CENTER;
                    break;
                }
                if(scaleType.equalsIgnoreCase("CENTER_INSIDE")) {
                    this.scaleType = ImageView.ScaleType.CENTER_INSIDE;
                    break;
                }
                if(scaleType.equalsIgnoreCase("CENTER_CROP")) {
                    this.scaleType = ImageView.ScaleType.CENTER_CROP;
                    break;
                }
                if(scaleType.equalsIgnoreCase("FIT_START")) {
                    this.scaleType = ImageView.ScaleType.FIT_START;
                    break;
                }
                if(scaleType.equalsIgnoreCase("FIT_END")) {
                    this.scaleType = ImageView.ScaleType.FIT_END;
                    break;
                }
            }while (false);
        }
    }
}
