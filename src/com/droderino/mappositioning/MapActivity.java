package com.droderino.mappositioning;

import com.droderino.mappositioning.Positioning;
import com.droderino.mappositioning.PositioningImpl;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapActivity extends Activity {

	
	private GoogleMap map;
	
	private Positioning posManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

		posManager = new PositioningImpl(this, map);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		posManager.start();
		posManager.startTracking();
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		posManager.stop();
		posManager.stopTracking();
	}
}
