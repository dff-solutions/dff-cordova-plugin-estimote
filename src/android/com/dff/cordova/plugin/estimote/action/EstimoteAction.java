package com.dff.cordova.plugin.estimote.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.estimote.sdk.BeaconManager;

public class EstimoteAction extends CordovaAction {
	protected BeaconManager beaconManager;

	public EstimoteAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova, BeaconManager beaconManager) {
		super(action, args, callbackContext, cordova);
		
		this.beaconManager = beaconManager;
	}

}
