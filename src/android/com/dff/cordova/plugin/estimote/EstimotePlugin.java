package com.dff.cordova.plugin.estimote;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.action.Connect;
import com.dff.cordova.plugin.estimote.action.Disconnect;
import com.dff.cordova.plugin.estimote.action.EstimoteAction;
import com.dff.cordova.plugin.estimote.action.SetBackgroundScanPeriod;
import com.dff.cordova.plugin.estimote.action.SetForegroundScanPeriod;
import com.dff.cordova.plugin.estimote.action.StartMonitoring;
import com.dff.cordova.plugin.estimote.action.StartNearableDiscovery;
import com.dff.cordova.plugin.estimote.action.StartRanging;
import com.dff.cordova.plugin.estimote.action.StopMonitoring;
import com.dff.cordova.plugin.estimote.action.StopNearableDiscovery;
import com.dff.cordova.plugin.estimote.action.StopRanging;
import com.dff.cordova.plugin.estimote.error.BeaconErrorListener;
import com.dff.cordova.plugin.estimote.monitor.BeaconMonitoringListener;
import com.dff.cordova.plugin.estimote.nearable.NearableDiscoveryListener;
import com.dff.cordova.plugin.estimote.ranging.BeaconRangingListener;
import com.dff.cordova.plugin.estimote.scan.BeaconScanStatusListener;
import com.estimote.sdk.BeaconManager;

import android.Manifest;
import android.content.pm.PackageManager;

public class EstimotePlugin extends CommonPlugin {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.EstimotePlugin";
	
	private static String[] permissions = new String[] {
			Manifest.permission.BLUETOOTH,
			Manifest.permission.BLUETOOTH_ADMIN,
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.INTERNET,
			Manifest.permission.ACCESS_NETWORK_STATE
	};
	private HashMap<String, Class<? extends EstimoteAction>> actions = new HashMap<String, Class<? extends EstimoteAction>>();
	private BeaconManager beaconManager;
	private BeaconMonitoringListener  beaconMonitoringListener;
	private BeaconScanStatusListener scanStatusListener;
	private NearableDiscoveryListener nearableListener;
	private BeaconRangingListener rangingListener;
	private BeaconErrorListener errorListener;
	
	public EstimotePlugin() {
		super(LOG_TAG);
		actions.put(Connect.ACTION_NAME, Connect.class);
		actions.put(Disconnect.ACTION_NAME, Disconnect.class);
		actions.put(StartMonitoring.ACTION_NAME, StartMonitoring.class);
		actions.put(StopMonitoring.ACTION_NAME, StopMonitoring.class);
		actions.put(StartRanging.ACTION_NAME, StartRanging.class);
		actions.put(StopRanging.ACTION_NAME, StopRanging.class);
		actions.put(StartNearableDiscovery.ACTION_NAME, StartNearableDiscovery.class);
		actions.put(StopNearableDiscovery.ACTION_NAME, StopNearableDiscovery.class);
		actions.put(SetBackgroundScanPeriod.ACTION_NAME, SetBackgroundScanPeriod.class);
		actions.put(SetForegroundScanPeriod.ACTION_NAME, SetForegroundScanPeriod.class);
	}
	
   /**
	* Called after plugin construction and fields have been initialized.
	*/
	@Override
	public void pluginInitialize() {
		super.pluginInitialize();
		beaconManager = new BeaconManager(this.cordova.getActivity());		
		beaconMonitoringListener = new BeaconMonitoringListener();
		beaconManager.setMonitoringListener(this.beaconMonitoringListener);
		
		scanStatusListener = new BeaconScanStatusListener();
		beaconManager.setScanStatusListener(scanStatusListener);
		
		nearableListener = new NearableDiscoveryListener();
		beaconManager.setNearableListener(nearableListener);
		
		rangingListener = new BeaconRangingListener();
		beaconManager.setRangingListener(this.rangingListener);
		
		errorListener = new BeaconErrorListener();
		beaconManager.setErrorListener(errorListener);
	}
	
    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking		Flag indicating if multitasking is turned on for app
     */
    public void onResume(boolean multitasking) {
    	super.onResume(multitasking);
    	
    	if (!hasPermissions()) {
    		this.cordova.requestPermissions(this, 0, permissions);
    	}
    }
    
    /**
     * The final call you receive before your activity is destroyed.
     */
	@Override
	public void onDestroy() {		
		super.onDestroy();
		this.beaconManager.disconnect();
	}
	
    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions,
            int[] grantResults) throws JSONException {
    	
    	super.onRequestPermissionResult(requestCode, permissions, grantResults);
    	
    	CordovaPluginLog.d(LOG_TAG, "onRequestPermissionResult: " + requestCode);
    
    	for (int i = 0; i < grantResults.length; i++) {
    		int r = grantResults[i];
    		String p = permissions[i];
    		
    		if (r == PackageManager.PERMISSION_DENIED) {
    			CordovaPluginLog.d(LOG_TAG, "permission denied for: " + p);
    		}
    		else if (r == PackageManager.PERMISSION_GRANTED) {
    			CordovaPluginLog.d(LOG_TAG, "permission granted for: " + p);
    		}   		    		
    	}
    }
	
    /**
     * Executes the request.
     *
     * This method is called from the WebView thread.
     * To do a non-trivial amount of work, use:
     * cordova.getThreadPool().execute(runnable);
     *
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action The action to execute.
     * @param args The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return Whether the action was valid.
     */
 	@Override
     public boolean execute(String action
     		, final JSONArray args
     		, final CallbackContext callbackContext)
         throws JSONException {
 		
     	CordovaPluginLog.i(LOG_TAG, "call for action: " + action + "; args: " + args);
     	    	
     	CordovaAction cordovaAction = null;
    	
    	if ("onEnteredRegion".equals(action)) {
    		this.beaconMonitoringListener.setOnEnteredRegionCallback(callbackContext);
    		return true;
    	}
    	else if ("onExitedRegion".equals(action)) {
    		this.beaconMonitoringListener.setOnExitedRegionCallback(callbackContext);
    		return true;
    	}
    	else if ("onScanStart".equals(action)) {
    		this.scanStatusListener.setOnScanStartCallback(callbackContext);    		
    		return true;    		
    	}
    	else if ("onScanStop".equals(action)) {
    		this.scanStatusListener.setOnScanStopCallback(callbackContext);    		
    		return true;    		
    	}
    	else if ("onNearablesDiscovered".equals(action)) {
    		this.nearableListener.setCallBack(callbackContext);
    		return true;
    	}
    	else if ("onBeaconsDiscovered".equals(action)) {
    		this.rangingListener.setCallBack(callbackContext);
    		return true;
    	}
    	else if ("onError".equals(action)) {
    		this.errorListener.setCallBack(callbackContext);
    		return true;
    	}
    	else if (actions.containsKey(action)) {     		
     		Class<? extends EstimoteAction> actionClass = actions.get(action);
     		
     		CordovaPluginLog.d(LOG_TAG, "found action: " + actionClass.getName());
     		
     		try {
				cordovaAction = actionClass.getConstructor(String.class
						, JSONArray.class
						, CallbackContext.class
						, CordovaInterface.class
						, BeaconManager.class
					)
					.newInstance(action, args, callbackContext, this.cordova, this.beaconManager);
			} catch (InstantiationException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (IllegalAccessException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (InvocationTargetException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (SecurityException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			}
     	}
    	
    	if (cordovaAction != null) {
    		cordova.getThreadPool().execute(cordovaAction);
            return true;
    	} 
     	
     	return super.execute(action, args, callbackContext);
     }
 	
 	private boolean hasPermissions() { 		
 		for (String permission : permissions) {
 			CordovaPluginLog.d(LOG_TAG, "check permission:" + permission);
 			if (!this.cordova.hasPermission(permission)) {
 				return false;
 			}
 		}
 		
 		return true;
 	}
}
