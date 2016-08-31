package com.dff.cordova.plugin.estimote.nearable;

import java.util.List;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.json.model.JsonNearable;
import com.estimote.sdk.BeaconManager.NearableListener;
import com.estimote.sdk.Nearable;

public class NearableDiscoveryListener extends AbstractPluginListener implements NearableListener {
	private static final String LOG_TAG = "com.dff.cordova.plugin.estimote.nearable.NearableDiscoveryListener";

	@Override
	public void onNearablesDiscovered(List<Nearable> nearables) {
		try {
			this.sendPluginResult(JsonNearable.toJson(nearables));
		}
		catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
		}
	}

}
