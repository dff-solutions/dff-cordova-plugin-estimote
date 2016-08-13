package com.dff.cordova.plugin.estimote;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.estimote.action.StartMonitoring;
import com.dff.cordova.plugin.estimote.action.StopMonitoring;
import com.estimote.sdk.BeaconManager;

public class EstimotePlugin extends CommonPlugin {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.EstimotePlugin";
	
	private boolean serviceConnected = false;
	private BeaconManager beaconManager;
	private BeaconMonitoringListener  beaconMonitoringListener;
	
	public EstimotePlugin() {
		super(LOG_TAG);
	}
	
   /**
	* Called after plugin construction and fields have been initialized.
	*/
	@Override
	public void pluginInitialize() {
		super.pluginInitialize();
		beaconManager = new BeaconManager(this.cordova.getActivity());
		
		beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
		    @Override
		    public void onServiceReady() {
		    	serviceConnected = true;
		    }
		});
		
		beaconMonitoringListener = new BeaconMonitoringListener();
		beaconManager.setMonitoringListener(this.beaconMonitoringListener);
	}
	
    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking		Flag indicating if multitasking is turned on for app
     */
    public void onResume(boolean multitasking) {
    	super.onResume(multitasking);
    	
    	// SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
    
    /**
     * The final call you receive before your activity is destroyed.
     */
	@Override
	public void onDestroy() {		
		super.onDestroy();
		this.beaconManager.disconnect();
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
     	
     	if (!serviceConnected) {
     		callbackContext.error("service not connected");
     		return false;
     	}
     	
     	CordovaAction cordovaAction = null;
    	
    	if (action.equals(StartMonitoring.ACTION_NAME)) {
    		cordovaAction = new StartMonitoring(
    				action,
    				args,
    				callbackContext,
    				this.cordova,
    				this.beaconManager
				);
    	}
    	else if (action.equals(StopMonitoring.ACTION_NAME)) {
    		cordovaAction = new StopMonitoring(
    				action,
    				args,
    				callbackContext,
    				this.cordova,
    				this.beaconManager
				);
    	}
    	else if ("onRegionChange".equals(action)) {
    		this.beaconMonitoringListener.setCallBack(callbackContext);
    	}    	
    	
    	if (cordovaAction != null) {
    		cordova.getThreadPool().execute(cordovaAction);
            return true;
    	} 
     	
     	return super.execute(action, args, callbackContext);
     }
}
