package com.letiko.opengldemo.controller;

import java.util.Locale;
import android.os.AsyncTask;
//import android.os.Debug;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
//import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import com.letiko.opengldemo.R;


public class SplashActivity extends Activity {
	//private static final String LOG_TAG = "SplashActivity";
	private WebView eulaview;
	//private final Activity activity = this;
	private ViewGroup mainview;
	//private RelativeLayout rlayout;
	private LinearLayout llayout;
	private Animation fadein, fadeout;
	private AnimationSet animationSet1, animationSet2;
	private boolean isLogoLetikoEnabled, isEulaEnabled;
	private Handler handler = new Handler(); // Set handler to switch splash screens
    private final int SPLASH_DISPLAY_LENGTH = 2000; 	// Set the display time, in milliseconds (or extract it out as a configurable parameter)
    private final int SPLASH_FADEIN_LENGTH = 500; 	// Set the display time, in milliseconds
    private final int SPLASH_FADEOUT_LENGTH = 1000; 	// Set the display time, in milliseconds
    private SharedPreferences sp;
    private String lang = null, eulatext = null;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    //Debug.startMethodTracing("/storage/sdcard0/Download/lalala");
	    super.onCreate(savedInstanceState);
	}
	
	
	@Override
	protected void onResume(){
        super.onResume();
        isLogoLetikoEnabled = true;
        isEulaEnabled = true;

        animationSet1 = new AnimationSet(true);
        fadein = new AlphaAnimation(0.0f, 1.0f);
		fadein.setDuration(SPLASH_FADEIN_LENGTH);
		fadeout = new AlphaAnimation(1.0f, 0.0f);
        fadeout.setStartOffset(1000);
		fadeout.setDuration(SPLASH_FADEOUT_LENGTH);
		animationSet1.addAnimation(fadein);
		animationSet1.addAnimation(fadeout);
        
		animationSet2 = new AnimationSet(true);
        fadein = new AlphaAnimation(0.0f, 1.0f);
		fadein.setDuration(SPLASH_FADEIN_LENGTH);
		fadeout = new AlphaAnimation(1.0f, 0.0f);
        fadeout.setStartOffset(1000);
		fadeout.setDuration(SPLASH_FADEOUT_LENGTH);
		animationSet2.addAnimation(fadein);
		animationSet2.addAnimation(fadeout);
/*
		fadein.setInterpolator(new AccelerateInterpolator());
  		fadeout.setInterpolator(new DecelerateInterpolator());
*/
		eulatext = getResources().getString(R.string.eula_text);
        showSplashLogoLetiko();
    }
	

	protected void showSplashLogoLetiko() {
        final Runnable showEula = new Runnable() {
    			@Override
    			public void run() {
    				showEULA();
    			}};
        if (isLogoLetikoEnabled){
        		handler.postDelayed(showEula, SPLASH_DISPLAY_LENGTH);	  //1st handler
        	    setContentView(R.layout.splash_logo_letiko);
        	    //mainview = (ViewGroup)findViewById(android.R.id.content);
	    		llayout = (LinearLayout)findViewById(R.id.splashlogoletiko);
	    		llayout.startAnimation(animationSet1);
		} else {showEULA();}  
	}
	
	
	protected void showEULA() {
		if (isEulaEnabled) {
			Button button;
			TextView  tv;
		    //getWindow().clearFlags(WindowManager.LayoutParams.);
			setContentView(R.layout.splash_eula);
			mainview = (ViewGroup)findViewById(android.R.id.content);
			//mainview.startAnimation(fadein);
			eulaview = (WebView)findViewById(R.id.agreementView);
			eulaview.setBackgroundColor(0xFFe5e5e5);

			eulaview.loadDataWithBaseURL("", eulatext, "text/html", "UTF-8","");

			//disable text selection from within WebView
			eulaview.setOnLongClickListener(new WebView.OnLongClickListener() {
	            public boolean onLongClick(View v) {
	                return true;
	            }
	        });
			tv = (TextView)findViewById(R.id.textheader);
			button = (Button)findViewById(R.id.agreementButton);

			tv.setText((getResources().getString(R.string.eula_header)));
			button.setText((getResources().getString(R.string.eula_button_ok)));
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					goNextActivity();}
				});
		}
		else {
			goNextActivity();
		}	
	}

	protected void goNextActivity(){
		SplashActivity.this.finish();
		Intent inte = new Intent();
		inte.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		inte.setClass(getBaseContext(), DashboardActivity.class);
		startActivity(inte);		
	}

}