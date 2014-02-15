package com.droderino.mappositioning;

import java.util.Observable;

public class Tracking extends Observable{

	private boolean isTracking;
	
	public Tracking()
	{
		isTracking = false;
	}

	public void enableTracking()
	{
		this.isTracking = true;
		setChanged();
		notifyObservers(isTracking);
	}
	
	public void disableTracking()
	{
		this.isTracking = false;
		setChanged();
		notifyObservers(isTracking);
	}
}
