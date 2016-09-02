package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.json.model.JsonRegion;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

public class StartRanging extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.StartRanging";
	public static final String ACTION_NAME = "startRanging";

	public StartRanging(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject jsonArgs = this.args.getJSONObject(0);
			
			if (jsonArgs != null) {
				Region region = JsonRegion.fromJson(jsonArgs);
				CordovaPluginLog.d(LOG_TAG, "startRanging: " + region.toString());
				this.beaconManager.startRanging(region);
				this.callbackContext.success();
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
