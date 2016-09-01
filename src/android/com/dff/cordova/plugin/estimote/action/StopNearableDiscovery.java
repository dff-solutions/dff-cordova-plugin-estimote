package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.estimote.sdk.BeaconManager;

public class StopNearableDiscovery extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.StopNearableDiscovery";
	public static final String ACTION_NAME = "stopNearableDiscovery";

	public StopNearableDiscovery(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		String scanId;
		
		try {
			JSONObject jsonArgs = this.args.getJSONObject(0);
			
			if (jsonArgs != null) {
				if (jsonArgs.isNull("scanId")) {
					throw new Exception("scanId missing");
				}
				else {
					scanId = jsonArgs.getString("scanId");
					this.beaconManager.stopNearableDiscovery(scanId);
					this.callbackContext.success();
				}				
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
