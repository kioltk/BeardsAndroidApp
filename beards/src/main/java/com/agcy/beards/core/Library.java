package com.agcy.beards.core;

import com.agcy.Models.Beard;
import com.agcy.beards.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 22.01.14.
 */
public class Library {

    public static Beard get(int id){
        for(Beard beard:list){
            if(beard.id==id)
                return beard;
        }
        return null;
    }

    public static ArrayList<Beard> list = new ArrayList<Beard>(){{

        add(
                new Beard(){{

                    id = 0;
                    title = "Full beard";
                    description = "The full beard is the classic expression of the male beard. all about beards strongly recommends the full beard as the best choice for those who can grow a full beard.";
                    guide = "Follow the instructions on the growing a beard page. When growing a full beard, be sure to define its shape properly. See: Designing a neck line for your full beard. For the cheek line, leave it natural. If you feel the natural cheek line is too high, define a line at an angle from the sideburn in front of the ear down to the outer edge of the mustache. Do not define the cheek line too low. A too-low cheek line gives the appearance of a beard that's been gutted. Also see: Choosing a cheek line for your full beard.";
                    imageId = R.drawable.beard1;

                }}
        );
        add(
                new Beard(){{

                    id = 1;
                    title = "Goatee and Mustache";
                    description = "The goatee and mustache combination is one of the most popular beard styles. It is a good choice for those with good growth in the mustache and chin areas but with weak growth on the cheeks.";
                    guide = "When growing a goatee and mustache for the first time, it is advisable to allow a larger area to grow than the size that you think that you would like. This will allow you more flexibility in determining the size of the coverage area for the goatee and mustache. Alternatively, you could start out as if you are growing a full beard and let all of your facial hair grow. Once you have achieved enough growth to make a proper determination, then you can shape and trim it down to the desired size. The typical shape of the goatee and mustache is similar to an oval, with the mustache forming the top of the oval. You may experiment with variations on the shape, but it is a good idea to start out with the oval-like shape for your first time.";
                    imageId = R.drawable.beard4;

                }}
        );
        add(
                new Beard(){{

                    id = 2;
                    title = "Chin curtain";
                    description = "The goatee and mustache combination is one of the most popular beard styles. It is a good choice for those with good growth in the mustache and chin areas but with weak growth on the cheeks.";
                    guide = "When growing a goatee and mustache for the first time, it is advisable to allow a larger area to grow than the size that you think that you would like. This will allow you more flexibility in determining the size of the coverage area for the goatee and mustache. Alternatively, you could start out as if you are growing a full beard and let all of your facial hair grow. Once you have achieved enough growth to make a proper determination, then you can shape and trim it down to the desired size. The typical shape of the goatee and mustache is similar to an oval, with the mustache forming the top of the oval. You may experiment with variations on the shape, but it is a good idea to start out with the oval-like shape for your first time.";
                    imageId = R.drawable.beard2;

                }}
        );

    }};




}
