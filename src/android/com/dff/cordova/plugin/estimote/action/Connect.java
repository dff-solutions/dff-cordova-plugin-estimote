package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.BeaconManager.ServiceReadyCallback;

public class Connect extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.Connect";
	public static final String ACTION_NAME = "connect";

	public Connect(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		
		this.beaconManager.connect(new ServiceReadyCallback() {
			
			@Override
			public void onServiceReady() {
				CordovaPluginLog.d(LOG_TAG, "service ready");
				callbackContext.success();				
			}
		});
	}

}
