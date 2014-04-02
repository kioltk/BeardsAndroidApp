package com.agcy.beards;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
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

import com.agcy.beards.Models.Guide;
import com.agcy.beards.core.Library;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");
        int id =  bundle.getInt("id", 0);
        Guide guide = null;
        if(type.equals("beard")){
            guide = Library.getBeard(id);
            ((TextView)findViewById(R.id.actionBarTitle)).setText("beard");
        }

        if(type.equals("tip")){
            guide = Library.getTip(id);


            ((TextView)findViewById(R.id.actionBarTitle)).setText("tip");
        }
        getFragmentManager().beginTransaction()
                .add(R.id.container, new GuideFragment(guide, this, getFragmentManager()))
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beard, menu);
        return true;
    }

    public void backClick(View view) {
        onBackPressed();
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


    public static class GuideFragment extends Fragment {

        Guide guide;
        Context mContext;
        FragmentManager fm;
        public GuideFragment(Guide guide, Context context, FragmentManager fm) {
            this.guide = guide;
            mContext = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_guide, container, false);

            TextView titleView = (TextView) rootView.findViewById(R.id.title);
            TextView descriptionView = (TextView) rootView.findViewById(R.id.description);
            final ImageView backgroundView = (ImageView) rootView.findViewById(R.id.background);
            final ImageView backgroundBlurView = (ImageView) rootView.findViewById(R.id.backgroundBlur);
            final Button guideButton = (Button) rootView.findViewById(R.id.guideButton);
            Button closeButton = (Button) rootView.findViewById(R.id.popupClose);

            TextView guideTypeView = (TextView) rootView.findViewById(R.id.guideType);
            TextView guideContentView =  (TextView) rootView.findViewById(R.id.guideContent);

            final View contentView = rootView.findViewById(R.id.content);
            final View popupView = rootView.findViewById(R.id.popup);

            titleView.setText(guide.title.replace(" ","\n"));
            descriptionView.setText(guide.description);
            backgroundView.setImageDrawable(guide.getImageDrawable());

            final Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
            fadeIn.setDuration(250);

            final Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
            fadeOut.setDuration(250);

            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            popupView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    contentView.setVisibility(View.VISIBLE);
                    contentView.startAnimation(fadeIn);
                    popupView.startAnimation(fadeOut);

                }
            });


            guideTypeView.setText(guide.type);
            guideContentView.setText(guide.content);



            rootView.post(new Runnable() {
                @Override
                public void run() {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            final Bitmap bm = screen(rootView);
                            guideButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    backgroundBlurView.setImageBitmap(bm);
                                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {


                                            contentView.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {



                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                    popupView.startAnimation(fadeIn);
                                    popupView.setVisibility(View.VISIBLE);

                                    contentView.startAnimation(fadeOut);
                                }
                            });
                        }
                    });

                    t.start();
                }
            });


            return rootView;
        }
        static void blurfast(Bitmap bmp, int radius) {
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] pix = new int[w * h];
            bmp.getPixels(pix, 0, w, 0, 0, w, h);

            for(int r = radius; r >= 1; r /= 2) {
                for(int i = r; i < h - r; i++) {
                    for(int j = r; j < w - r; j++) {
                        int tl = pix[(i - r) * w + j - r];
                        int tr = pix[(i - r) * w + j + r];
                        int tc = pix[(i - r) * w + j];
                        int bl = pix[(i + r) * w + j - r];
                        int br = pix[(i + r) * w + j + r];
                        int bc = pix[(i + r) * w + j];
                        int cl = pix[i * w + j - r];
                        int cr = pix[i * w + j + r];

                        pix[(i * w) + j] = 0xFF000000 |
                                (((tl & 0xFF) + (tr & 0xFF) + (tc & 0xFF) + (bl & 0xFF) + (br & 0xFF) + (bc & 0xFF) + (cl & 0xFF) + (cr & 0xFF)) >> 3) & 0xFF |
                                (((tl & 0xFF00) + (tr & 0xFF00) + (tc & 0xFF00) + (bl & 0xFF00) + (br & 0xFF00) + (bc & 0xFF00) + (cl & 0xFF00) + (cr & 0xFF00)) >> 3) & 0xFF00 |
                                (((tl & 0xFF0000) + (tr & 0xFF0000) + (tc & 0xFF0000) + (bl & 0xFF0000) + (br & 0xFF0000) + (bc & 0xFF0000) + (cl & 0xFF0000) + (cr & 0xFF0000)) >> 3) & 0xFF0000;
                    }
                }
            }
            bmp.setPixels(pix, 0, w, 0, 0, w, h);
        }
        Bitmap screen(View rootView){

            rootView.setDrawingCacheEnabled(true);
            rootView.buildDrawingCache();
            Bitmap  bm = rootView.getDrawingCache();
            blurfast(bm, 4);

            return bm;
        }





    }



}
