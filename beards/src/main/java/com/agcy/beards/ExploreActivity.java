package com.agcy.beards;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.agcy.beards.Adapters.ExploreListAdapter;
import com.agcy.beards.Models.Guide;
import com.agcy.beards.core.Library;

import java.util.ArrayList;

public class ExploreActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Library.context = this;

        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ExploreFragment(this,type))
                    .commit();

        }
        if(type.equals("explore")){
            ((TextView)findViewById(R.id.actionBarTitle)).setText("explore");
        }
        if(type.equals("tips")){
            ((TextView)findViewById(R.id.actionBarTitle)).setText("tips");
        }
    }




    public void backClick(View view) {
        onBackPressed();
    }

    public static class ExploreFragment extends Fragment {

        Context context;
        String type;
        public ExploreFragment(Context context,String type) {
            this.context = context;
            this.type = type;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_explore, container, false);
            ListView exploreList = (ListView) rootView.findViewById(R.id.exploreList);

            ArrayList<Guide> guides =null;
            if(type.equals("beard"))
                guides = Library.getBeardsStyles();
            if(type.equals("tip"))
                guides = Library.getTips();
            ExploreListAdapter exploreAdapter = new ExploreListAdapter(context, guides);
            exploreList.setAdapter(exploreAdapter);
            exploreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(context, GuideActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", type);
                    bundle.putInt("id", (int) id);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });

            return rootView;
        }
    }


}
