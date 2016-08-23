package com.dff.cordova.plugin.estimote.monitor;

import java.util.List;

import org.apache.cordova.CallbackContext;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;

public class BeaconMonitoringListener implements MonitoringListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.BeaconMonitoringListener";
	
	public EnteredRegionCallbackHandler enteredRegionCallbackHandler = new EnteredRegionCallbackHandler();
	public ExitedRegionCallbackHandler exitedRegionCallbackHandler = new ExitedRegionCallbackHandler();
	
	public void setOnEnteredRegionCallback(CallbackContext callbackContext) {
		this.enteredRegionCallbackHandler.setCallBack(callbackContext);
	}
	
	public void setOnExitedRegionCallback(CallbackContext callbackContext) {
		this.exitedRegionCallbackHandler.setCallBack(callbackContext);
	}

	@Override
	public void onEnteredRegion(Region region, List<Beacon> beacons) {
		this.enteredRegionCallbackHandler.onEnteredRegion(region, beacons);
	}

	@Override
	public void onExitedRegion(Region region) {
		this.exitedRegionCallbackHandler.onExitedRegion(region);
	}

}
