package com.dff.cordova.plugin.estimote.monitor;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.json.model.JsonBeacon;
import com.dff.cordova.plugin.estimote.json.model.JsonRegion;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Region;

public class EnteredRegionCallbackHandler extends AbstractPluginListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.EnteredRegionCallbackHandler";
	
	public void onEnteredRegion(Region region, List<Beacon> beacons) {
		CordovaPluginLog.d(LOG_TAG, "entered region: " + region.toString());
		
		try {
			JSONObject jsonEnteredRegion = JsonRegion.toJson(region);
			jsonEnteredRegion.put("beacons", JsonBeacon.toJson(beacons));			
			this.sendPluginResult(jsonEnteredRegion);
		} catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage());
			this.sendPluginResult(e);
		}
	}
}
