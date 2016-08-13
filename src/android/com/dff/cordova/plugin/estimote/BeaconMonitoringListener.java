package com.dff.cordova.plugin.estimote;

import java.util.List;

import org.json.JSONObject;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;

public class BeaconMonitoringListener extends AbstractPluginListener implements MonitoringListener {

	@Override
	public void onEnteredRegion(Region region, List<Beacon> beacons) {
		JSONObject jsonRegion = JsonRegion.toJson(region);
		
		JSONObject jsonRegionChange = new JSONObject();
		jsonRegionChange.put("event", "exited");
		jsonRegion.put("region", jsonRegion);
		
		this.sendPluginResult(jsonRegionChange);
	}

	@Override
	public void onExitedRegion(Region region) {
		JSONObject jsonRegion = JsonRegion.toJson(region);
		
		JSONObject jsonRegionChange = new JSONObject();
		jsonRegionChange.put("event", "exited");
		jsonRegion.put("region", jsonRegion);
		
		this.sendPluginResult(jsonRegionChange);

	}

}
