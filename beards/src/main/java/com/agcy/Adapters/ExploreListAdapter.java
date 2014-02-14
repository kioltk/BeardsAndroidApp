package com.agcy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agcy.Models.Guide;
import com.agcy.beards.R;
import com.agcy.beards.core.Imager;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 21.01.14.
 */
public class ExploreListAdapter extends ArrayAdapter<Guide> {
    Context context;
    public ExploreListAdapter(Context context,ArrayList<Guide> guides) {
        super(context,R.layout.item_guide, guides);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_guide, parent, false);



        ImageView imageView = (ImageView) view.findViewById(R.id.exploreImage);
        TextView textView = (TextView) view.findViewById(R.id.exploreText);



        Guide guide = getItem(position);

        imageView.setImageDrawable(Imager.getDrawable(guide.imageId));
        if(guide.scaleType!=null)
            imageView.setScaleType(guide.scaleType);
        textView.setText(guide.title);

        return view;

    }


}
