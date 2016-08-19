package com.dff.cordova.plugin.estimote;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.json.model.JsonRegion;
import com.estimote.sdk.Region;

public class ExitedRegionCallbackHandler extends AbstractPluginListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.ExitedRegionCallbackHandler";
	
	public void onExitedRegion(Region region) {
		CordovaPluginLog.d(LOG_TAG, "exited region: " + region.toString());
		
		try {
			this.sendPluginResult(JsonRegion.toJson(region));
		} catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage());
			this.sendPluginResult(e);
		}

	}
}
