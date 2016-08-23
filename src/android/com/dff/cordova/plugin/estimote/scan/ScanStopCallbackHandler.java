package com.dff.cordova.plugin.estimote.scan;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;

public class ScanStopCallbackHandler extends AbstractPluginListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.StopScanCallbackHandler";
	
	public void onScanStop() {
		CordovaPluginLog.d(LOG_TAG, "scan stoped");
		
		this.sendPluginResult();
	}
}
