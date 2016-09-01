package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.estimote.sdk.BeaconManager;

public class StartNearableDiscovery extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.StartNearableDiscovery";
	public static final String ACTION_NAME = "startNearableDiscovery";

	public StartNearableDiscovery(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();		
		
		this.callbackContext.success(this.beaconManager.startNearableDiscovery());
	}

}
