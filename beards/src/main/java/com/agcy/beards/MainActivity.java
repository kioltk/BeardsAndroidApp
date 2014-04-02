package com.agcy.beards;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agcy.beards.core.Imager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this))
                    .commit();
        }
        final ImageView background = (ImageView) findViewById(R.id.background);
        background.setImageDrawable(Imager.getRandomBeard());

        Timer myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() { // Определяем задачу
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        final Animation fadeIn = new AlphaAnimation(0, 1);
                        fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
                        fadeIn.setStartOffset(500);
                        fadeIn.setDuration(750);

                        final Animation fadeOut = new AlphaAnimation(1, 0);
                        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
                        fadeOut.setStartOffset(0);
                        fadeOut.setDuration(750);
                        fadeOut.setAnimationListener(new Animation.AnimationListener() {
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

                        background.startAnimation(fadeOut);

                    }
                });
            }

        }, 6L * 1000, 6L * 1000);


    }

    @Override
    public void onResume(){

        super.onResume();

        Imager.context = this;

    }




    Boolean aboutOpened = false;

    public void aboutClick(View view) {

        if(aboutOpened == null)
            return;

        final View container =  findViewById(R.id.container);
        final View about = findViewById(R.id.about);

        if(!aboutOpened){


            final Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
            fadeIn.setStartOffset(0);
            fadeIn.setDuration(750);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    aboutOpened = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            final Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
            fadeOut.setStartOffset(0);
            fadeOut.setDuration(250);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    container.setVisibility(View.GONE);
                    about.setVisibility(View.VISIBLE);
                    about.startAnimation(fadeIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            container.startAnimation(fadeOut);

            ((Button)view).setText("Menu");
        }else{


            final Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new AccelerateInterpolator()); // and this
            fadeIn.setStartOffset(0);
            fadeIn.setDuration(750);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    aboutOpened = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            final Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
            fadeOut.setStartOffset(0);
            fadeOut.setDuration(250);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    about.setVisibility(View.GONE);
                    container.setVisibility(View.VISIBLE);
                    container.startAnimation(fadeIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            about.startAnimation(fadeOut);

            ((Button)view).setText("About");

        }

        aboutOpened = null;

    }

    public static class PlaceholderFragment extends Fragment {
        Context context;
        public PlaceholderFragment(Context context) {
            this.context = context;
        }
        public Intent getNavigationIntent(int position){
           Intent intent =  new Intent(context, ExploreActivity.class);
            Bundle bundle = new Bundle();
            switch (position){
                case 0:
                    bundle.putString("type","beard");
                    break;
                case 1:
                    bundle.putString("type","tip");
                    break;
                case 2:
                    //intent = new Intent(context, MainActivity.class);
                    break;
            }
            intent.putExtras(bundle);
            return intent;
        }
        public View.OnClickListener getNavigationListener(final int position){


            final Intent intent = getNavigationIntent(position);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    context.startActivity(intent);
                }
            };

            return listener;
        }

        public Drawable getButtonDrawable(int position){
            Drawable drawable = null;
            switch (position){
                case 0:
                    drawable = context.getResources().getDrawable(R.drawable.search);
                    break;
                case 1:
                    drawable = context.getResources().getDrawable(R.drawable.chainsaw);
                    break;
                case 2:
                    drawable = context.getResources().getDrawable(R.drawable.ic_white);
                    break;
            }
            return drawable;
        }
        public String getButtonText(int position){
            String text = null;
            switch (position){
                case 0:
                    text = "explore";
                    break;
                case 1:
                    text = "tips";
                    break;
                case 2:
                    text = "my";
                    break;
            }
            return text;
        }
        public View getButton(LayoutInflater inflater,int position){

            ViewGroup navigationButton = (ViewGroup) inflater.inflate(R.layout.navigation_button,null);

            View button = navigationButton.findViewById(R.id.navigationButton);
            TextView textView = (TextView) navigationButton.findViewById(R.id.navigationText);
            ImageView imageView = (ImageView) navigationButton.findViewById(R.id.navigationImage);

            button.setOnClickListener(getNavigationListener(position));
            textView.setText(getButtonText(position));

            imageView.setImageDrawable(getButtonDrawable(position));

            return navigationButton;

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            for(int i=0;i<2;i++){

                View navigationButton = getButton(inflater, i);
                ((ViewGroup) rootView).addView(navigationButton);

            }
            return rootView;
        }
    }

}
