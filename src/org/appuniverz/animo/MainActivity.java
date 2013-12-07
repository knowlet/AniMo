package org.appuniverz.animo;

import org.appuniverz.animo.fragments.HomeTab;
import org.appuniverz.animo.fragments.PostAdoptionTab;
import org.appuniverz.animo.fragments.SearchTab;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;


import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;


public class MainActivity extends FragmentActivity {

	private final static String TAG = "MainActivity";
	
	private FragmentTabHost tabHost;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(TAG, "Activity init Parse");
		
		// Parse init
		Parse.initialize(this,  "****************************************", "****************************************");


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
			
		
		// Lock Orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Log.i(TAG, "Activity init Tabs");
		
		// Init tabHost 
		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// Add Tabs
		tabHost.addTab(
				tabHost.newTabSpec("Home").setIndicator(
						getString(R.string.tab_title_home)),
				HomeTab.class, null);
		
		tabHost.addTab(
				tabHost.newTabSpec("Search").setIndicator(
						getString(R.string.tab_title_search)),
				SearchTab.class, null);
		
		tabHost.addTab(
				tabHost.newTabSpec("PostAdoption").setIndicator(
						getString(R.string.tab_title_postadoption)),
				PostAdoptionTab.class, null);
		
		
		


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			final ActionBar actionBar = getActionBar();

			actionBar.show();
		}	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
