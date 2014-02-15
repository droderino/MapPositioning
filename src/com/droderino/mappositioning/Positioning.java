package com.droderino.mappositioning;

import com.google.android.gms.maps.model.LatLng;

public interface Positioning {

	public abstract LatLng lastLocation();

	public abstract void start();

	public abstract void stop();

	public abstract void startTracking();
	
	public abstract void stopTracking();
	
	public abstract boolean isTracking();
}