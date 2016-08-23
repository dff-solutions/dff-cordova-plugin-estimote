package com.dff.cordova.plugin.estimote.scan;

import org.apache.cordova.CallbackContext;

import com.estimote.sdk.BeaconManager.ScanStatusListener;

public class BeaconScanStatusListener implements ScanStatusListener {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.BeaconScanStatusListener";
	
	public ScanStartCallbackHandler scanStartCallbackHandler = new ScanStartCallbackHandler();
	public ScanStopCallbackHandler scanStopCallbackHandler = new ScanStopCallbackHandler();
	
	public void setOnScanStartCallback(CallbackContext callbackContext) {
		this.scanStartCallbackHandler.setCallBack(callbackContext);
	}
	
	public void setOnScanStopCallback(CallbackContext callbackContext) {
		this.scanStopCallbackHandler.setCallBack(callbackContext);
	}

	@Override
	public void onScanStart() {
		this.scanStartCallbackHandler.onScanStart();		
	}

	@Override
	public void onScanStop() {
		this.scanStopCallbackHandler.onScanStop();		
	}
}
