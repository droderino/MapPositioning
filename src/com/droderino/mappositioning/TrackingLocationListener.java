package com.droderino.mappositioning;

import java.util.Observable;
import java.util.Observer;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class TrackingLocationListener implements LocationListener, Observer {

	private boolean isTracking;
	
	public TrackingLocationListener()
	{
		this.isTracking = false;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d("TRACKING", "Tracking: " + isTracking + " lat: " + location.getLatitude() + " lon: " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if( data instanceof Boolean )
			this.isTracking = (Boolean)data;
		
	}

}
