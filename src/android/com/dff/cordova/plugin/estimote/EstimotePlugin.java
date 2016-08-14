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
import com.dff.cordova.plugin.estimote.action.EstimoteAction;
import com.dff.cordova.plugin.estimote.action.StartMonitoring;
import com.dff.cordova.plugin.estimote.action.StopMonitoring;
import com.estimote.sdk.BeaconManager;

public class EstimotePlugin extends CommonPlugin {
	public static final String LOG_TAG = "com.dff.cordova.plugin.estimote.EstimotePlugin";
	private HashMap<String, Class<? extends EstimoteAction>> actions = new HashMap<String, Class<? extends EstimoteAction>>();
	private boolean serviceConnected = false;
	private BeaconManager beaconManager;
	private BeaconMonitoringListener  beaconMonitoringListener;
	
	public EstimotePlugin() {
		super(LOG_TAG);
		actions.put(StartMonitoring.ACTION_NAME, StartMonitoring.class);
		actions.put(StopMonitoring.ACTION_NAME, StopMonitoring.class);
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
    	
    	if ("onEnteredRegion".equals(action)) {
    		this.beaconMonitoringListener.setOnEnteredRegionCallback(callbackContext);
    	}
    	else if ("onExitedRegion".equals(action)) {
    		this.beaconMonitoringListener.setOnExitedRegionCallback(callbackContext);
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
}
