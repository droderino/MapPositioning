package com.droderino.mappositioning;

import java.util.Observer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

public class PositioningImpl implements Positioning {

	private final int defaultZoomLevel = 15;
	private LocationManager locManager;
	private LocationListener mapListener, trackingListener;
	private Marker marker;
	private Tracking tracker;
	
	public PositioningImpl(Context context, GoogleMap map)
	{
		locManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
		LocationProvider locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);		
		LatLng lastLocation = this.lastLocation();
		
		marker = map.addMarker(new MarkerOptions().position(lastLocation));
		
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, defaultZoomLevel));
		
		tracker = new Tracking();
		mapListener = new MapLocationListener(map, marker);
		trackingListener = new TrackingLocationListener();
	}
	
	@Override
	public LatLng lastLocation()
	{
		Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		return new LatLng(location.getLatitude(), location.getLongitude());
	}
	
	@Override
	public void start()
	{
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, mapListener);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, trackingListener);
		tracker.addObserver((Observer)mapListener);
		tracker.addObserver((Observer)trackingListener);
	}
	
	@Override
	public void stop()
	{
		locManager.removeUpdates(mapListener);
		locManager.removeUpdates(trackingListener);
		tracker.deleteObservers();
	}

	@Override
	public void startTracking() {
		tracker.enableTracking();
	}

	@Override
	public void stopTracking() {
		tracker.disableTracking();
	}
}
