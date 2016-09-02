package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.estimote.sdk.BeaconManager;

public class SetForegroundScanPeriod extends EstimoteAction {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.action.SetForegroundScanPeriod";
	public static final String ACTION_NAME = "setForegroundScanPeriod";

	public SetForegroundScanPeriod(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova, beaconManager);
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			long scanPeriodMillis;
			long waitTimeMillis;
			JSONObject jsonArgs = this.args.getJSONObject(0);
			
			if (jsonArgs != null) {
				if (jsonArgs.isNull("scanPeriodMillis")) {
					throw new Exception("scanPeriodMillis missing");
				}
				else {
					scanPeriodMillis = jsonArgs.getLong("scanPeriodMillis");
				}
				
				if (jsonArgs.isNull("waitTimeMillis")) {
					throw new Exception("waitTimeMillis missing");
				}
				else {
					waitTimeMillis = jsonArgs.getLong("waitTimeMillis");
				}				
				
				CordovaPluginLog.d(LOG_TAG, "setForegroundScanPeriod: " + scanPeriodMillis + " " + waitTimeMillis);
				this.beaconManager.setForegroundScanPeriod(scanPeriodMillis, waitTimeMillis);
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
