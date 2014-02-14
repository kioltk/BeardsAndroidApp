package com.agcy.beards;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agcy.CustomViews.CirclePageIndicator;
import com.agcy.beards.core.Imager;

import java.util.Locale;

public class StartActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v13.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    static View.OnClickListener mListener;
    static Animation changeBackground;
    static ImageView background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        final View startView = findViewById(R.id.startView);
        background = (ImageView) findViewById(R.id.background);
        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);

        Imager.context = this;

        final SharedPreferences sp = getSharedPreferences("data",MODE_MULTI_PROCESS);
        final Boolean firstStart = sp.getBoolean("first_start", true);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(0);
        fadeOut.setDuration(500);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startView.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
        fadeIn.setStartOffset(0);
        fadeIn.setDuration(750);

        changeBackground = new AlphaAnimation(1, 0);
        changeBackground.setInterpolator(new AccelerateInterpolator()); // and this
        changeBackground.setStartOffset(0);
        changeBackground.setDuration(500);
        changeBackground.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                background.setImageDrawable(Imager.getRandomBeard());
                background.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(!firstStart){
                    navigateToMain();
                }
                else{

                    startView.startAnimation(fadeOut);
                    mListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor editor =  sp.edit();
                            editor.putBoolean("first_start",false);
                            editor.commit();
                            navigateToMain();
                        }
                    };

                }
            }
        }, 1500);




        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);


        indicator.setViewPager(mViewPager);
        indicator.setSnap(true);






    }
    public void navigateToMain(){
        final Context mContext = this;
        final Activity activity = this;
        final Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.start, menu);
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

    

    /**
     * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a SectionFragment (defined as a static inner class below).
            return SectionFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static final String getSectionInfo(int sectionNumber){
            switch (sectionNumber){
                case 1:
                    return "Every man ever thought about having a nice beard";
                case 2:
                    return "But not every man knows how to" +
                            System.getProperty ("line.separator") +
                            "We want to show and explain";
                case 3:
                    return "Ready?";
            }
            return "error";
        }
        private static final int getSectionDrawable(int sectionNumber){
            switch (sectionNumber){
                case 1:
                    return R.drawable.beard1;
                case 2:
                    return R.drawable.beard2;
                case 3:
                    return R.drawable.beard3;
            }
            return R.drawable.beard1;
        }
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SectionFragment newInstance(int sectionNumber) {
            SectionFragment fragment = new SectionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public SectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_start, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_info);

            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

            String info = getSectionInfo(sectionNumber);
            if(sectionNumber==3){


                final Button button = (Button) rootView.findViewById(R.id.start_button);

                final Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
                fadeIn.setStartOffset(1250);
                fadeIn.setDuration(1000);
                fadeIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        background.startAnimation(changeBackground);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        button.setOnClickListener(mListener);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                button.setVisibility(View.VISIBLE);
                button.startAnimation(fadeIn);
            }
            textView.setText(info);

            background.startAnimation(changeBackground);

            return rootView;
        }

    }

}
