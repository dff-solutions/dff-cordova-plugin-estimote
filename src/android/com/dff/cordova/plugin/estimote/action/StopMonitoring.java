package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.estimote.sdk.BeaconManager;

import android.graphics.Region;

public class StopMonitoring extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.StartMonitoring";
	public static final String ACTION_NAME = "stopMonitoring";

	public StopMonitoring(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		
		JSONObject jsonArgs = this.args.getJSONObject(0);
		
		try {
			if (jsonArgs != null) {
				Region region = JsonRegion.fromJson(jsonArgs);
				this.beaconManager.startMonitoring(region);
			}
			else {
				throw new Exception("args missing");
			}
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
		catch (Exception e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
		
		
	}

}
