package com.agcy.beards;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.agcy.Adapters.ExploreListAdapter;
import com.agcy.Models.Beard;
import com.agcy.beards.core.Library;

import java.util.ArrayList;

public class ExploreActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this))
                    .commit();

        }

        ((TextView)findViewById(R.id.actionBarTitle)).setText("explore");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.explore, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void backClick(View view) {
        onBackPressed();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Context mContext;
        public PlaceholderFragment(Context context) {
            mContext = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_explore, container, false);
            ListView exploreList = (ListView) rootView.findViewById(R.id.exploreList);

            ArrayList<Beard> beards = Library.list;

            ExploreListAdapter exploreAdapter = new ExploreListAdapter(mContext, beards);
            exploreList.setAdapter(exploreAdapter);

            exploreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(mContext, BeardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",(int) id);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }
            });

            return rootView;
        }
    }

}
