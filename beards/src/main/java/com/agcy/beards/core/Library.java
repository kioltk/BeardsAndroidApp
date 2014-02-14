package com.agcy.beards.core;

import android.widget.ImageView;

import com.agcy.Models.Guide;
import com.agcy.beards.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 22.01.14.
 */
public class Library {

    public static Guide getBeard(int id){
        for(Guide beard: beards){
            if(beard.id==id)
                return beard;
        }
        return null;
    }

    public static ArrayList<Guide> beards = new ArrayList<Guide>(){{

        add(
                new Guide(){{

                    id = 0;
                    title = "Full beard";
                    type = "Growing";
                    description = "The full beard is the classic expression of the male beard. all about beards strongly recommends the full beard as the best choice for those who can grow a full beard.";
                    content = "Follow the instructions on the growing a beard page. When growing a full beard, be sure to define its shape properly. See: Designing a neck line for your full beard. For the cheek line, leave it natural. If you feel the natural cheek line is too high, define a line at an angle from the sideburn in front of the ear down to the outer edge of the mustache. Do not define the cheek line too low. A too-low cheek line gives the appearance of a beard that's been gutted. Also see: Choosing a cheek line for your full beard.";
                    imageId = R.drawable.beard1;

                }}
        );
        add(
                new Guide(){{

                    id = 1;
                    title = "Goatee and Mustache";
                    type = "Growing";
                    description = "The goatee and mustache combination is one of the most popular beard styles. It is a good choice for those with good growth in the mustache and chin areas but with weak growth on the cheeks.";
                    content = "When growing a goatee and mustache for the first time, it is advisable to allow a larger area to grow than the size that you think that you would like. This will allow you more flexibility in determining the size of the coverage area for the goatee and mustache. Alternatively, you could start out as if you are growing a full beard and let all of your facial hair grow. Once you have achieved enough growth to make a proper determination, then you can shape and trim it down to the desired size. The typical shape of the goatee and mustache is similar to an oval, with the mustache forming the top of the oval. You may experiment with variations on the shape, but it is a good idea to start out with the oval-like shape for your first time.";
                    imageId = R.drawable.beard4;
                    scaleType = ImageView.ScaleType.CENTER_CROP;

                }}
        );
        add(
                new Guide(){{

                    id = 2;
                    title = "Chin curtain";
                    type = "Growing";
                    description = "The goatee and mustache combination is one of the most popular beard styles. It is a good choice for those with good growth in the mustache and chin areas but with weak growth on the cheeks.";
                    content = "When growing a goatee and mustache for the first time, it is advisable to allow a larger area to grow than the size that you think that you would like. This will allow you more flexibility in determining the size of the coverage area for the goatee and mustache. Alternatively, you could start out as if you are growing a full beard and let all of your facial hair grow. Once you have achieved enough growth to make a proper determination, then you can shape and trim it down to the desired size. The typical shape of the goatee and mustache is similar to an oval, with the mustache forming the top of the oval. You may experiment with variations on the shape, but it is a good idea to start out with the oval-like shape for your first time.";
                    imageId = R.drawable.beard2;

                }}
        );

    }};
    public static ArrayList<Guide> tips = new ArrayList<Guide>(){{
        add(new Guide(){{
            id = 0;
            title = "Coloring";
            type = "Coloring";
            content = "Берём краску иии красим!";
            description = "Делать это нужно на большой бороде.";
            imageId = R.drawable.coloring;
        }});
        add(new Guide(){{
            id = 1;
            title = "grooming";
            type = "Coloring";
            imageId = R.drawable.grooming;
        }});
        add(new Guide(){{
            id = 2;
            title = "trimming";
            description = "Бороду нужно приводить в порядок, а то будете как бомж =\\";
            type = "Trimming";
            content = "Берём ножнички, расчесочку, и стрижомся";
            imageId = R.drawable.trimming;
        }});
    }};


    public static Guide getTip(int id) {
        for(Guide tip: tips){
            if(tip.id==id)
                return tip;
        }
        return null;
    }
}
