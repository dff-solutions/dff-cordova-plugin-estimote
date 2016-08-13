package com.dff.cordova.plugin.estimote;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.json.model.JsonBeacon;
import com.dff.cordova.plugin.estimote.json.model.JsonRegion;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;

public class BeaconMonitoringListener extends AbstractPluginListener implements MonitoringListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.BeaconMonitoringListener";

	@Override
	public void onEnteredRegion(Region region, List<Beacon> beacons) {
		
		try {
			JSONObject jsonRegionChange = new JSONObject();
			jsonRegionChange.put("event", "entered");
			jsonRegionChange.put("region", JsonRegion.toJson(region));
			jsonRegionChange.put("beacons", JsonBeacon.toJson(beacons));
			
			this.sendPluginResult(jsonRegionChange);
		} catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage());
			this.sendPluginResult(e);
		}
	}

	@Override
	public void onExitedRegion(Region region) {
		JSONObject jsonRegion;
		JSONObject jsonRegionChange = new JSONObject();
		
		try {
			jsonRegionChange.put("event", "exited");			
			jsonRegion = JsonRegion.toJson(region);
			jsonRegion.put("region", jsonRegion);
			this.sendPluginResult(jsonRegionChange);
		} catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage());
			this.sendPluginResult(e);
		}

	}

}
