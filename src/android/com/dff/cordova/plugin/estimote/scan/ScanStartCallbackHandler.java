package com.dff.cordova.plugin.estimote.scan;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;

public class ScanStartCallbackHandler extends AbstractPluginListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.StartScanCallbackHandler";
	
	public void onScanStart() {
		CordovaPluginLog.d(LOG_TAG, "scan started");
		
		this.sendPluginResult();
	}
}
