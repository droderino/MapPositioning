package com.droderino.mappositioning;

import java.util.Observable;
import java.util.Observer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MapLocationListener implements LocationListener, Observer {

	
	private GoogleMap map;
	private Marker marker;
	private PolylineOptions options;
	private Polyline line;
	private boolean isTracking;
	
	public MapLocationListener(GoogleMap map, Marker marker)
	{
		this.map = map;
		this.marker = marker;
		
		options = new PolylineOptions();
		options.color(Color.BLUE);
		line = map.addPolyline(options);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		LatLng newPos = new LatLng(location.getLatitude(), location.getLongitude());
		marker.setPosition(newPos);
		map.animateCamera(CameraUpdateFactory.newLatLng(newPos));
		
		if(isTracking)
		{
			options.add(newPos);
			line.setPoints(options.getPoints());
		}
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
