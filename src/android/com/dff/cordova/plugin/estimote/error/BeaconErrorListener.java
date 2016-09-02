package com.dff.cordova.plugin.estimote.error;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.estimote.sdk.BeaconManager.ErrorListener;
import com.estimote.sdk.service.BeaconService;

public class BeaconErrorListener extends AbstractPluginListener implements ErrorListener {
	private static final String LOG_TAG = "com.dff.cordova.plugin.estimote.error.BeaconErrorListener";
	
	@Override
	public void onError(Integer errorId) {
		JSONObject jsonError = new JSONObject();
		
		try {
			String errorName = null;
			
			
			switch (errorId) {
			case BeaconService.ERROR_BLUETOOTH_SCANNER_UNSTABLE:
				errorName = "ERROR_BLUETOOTH_SCANNER_UNSTABLE";
				break;
			case BeaconService.ERROR_COULD_NOT_START_LOW_ENERGY_SCANNING:
				errorName = "ERROR_COULD_NOT_START_LOW_ENERGY_SCANNING";
				break;

			default:
				break;
			}
			
			jsonError.put("errorId", errorId);
			jsonError.put("errorName", errorName);
			this.sendPluginResult(jsonError);			
		} catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
		}

	}

}
