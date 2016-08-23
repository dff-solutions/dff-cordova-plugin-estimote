package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.estimote.sdk.BeaconManager;

public class Disconnect extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.Disconnect";
	public static final String ACTION_NAME = "disconnect";

	public Disconnect(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		
		this.beaconManager.disconnect();
		this.callbackContext.success();
	}

}
