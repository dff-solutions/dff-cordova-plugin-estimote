package com.dff.cordova.plugin.estimote.json.model;

import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.MacAddress;

public class JsonBeacon {
	public static JSONObject toJson(Beacon beacon)  throws JSONException {
		JSONObject jsonThread = new JSONObject();
		jsonThread.put("major", beacon.getMajor());
		jsonThread.put("measuredPower", beacon.getMeasuredPower());
		jsonThread.put("minor", beacon.getMinor());
		jsonThread.put("rssi", beacon.getRssi());
		jsonThread.put("macAddress", beacon.getMacAddress());
		jsonThread.put("proximityUUID", beacon.getProximityUUID());
		
		return jsonThread;
	}
	
	public static JSONArray toJson(List<Beacon> beacons) throws JSONException {
		JSONArray jsonBeacons = new JSONArray();
		
		if (beacons != null) {
			for	(Beacon b : beacons) {
				jsonBeacons.put(toJson(b));
			}
		}
		
		return jsonBeacons;		
	}
	
	public static Beacon fromJson(JSONObject jsonBeacon) throws JSONException {
		int major = 0;
		if (jsonBeacon.has("major")){
			major = jsonBeacon.getInt("major");
		}
		
		int measuredPower = 0;
		if (jsonBeacon.has("measuredPower")){
			measuredPower = jsonBeacon.getInt("measuredPower");
		}
		
		int minor = 0;
		if (jsonBeacon.has("minor")){
			minor = jsonBeacon.getInt("minor");
		}
		
		int rssi = 0;
		if (jsonBeacon.has("rssi")){
			rssi = jsonBeacon.getInt("rssi");
		}
		
		MacAddress macAddress = null;
		if (jsonBeacon.has("macAddress")) {
			macAddress = MacAddress.fromString(jsonBeacon.getString("macAddress"));
		}
		
		UUID proximityUUID = null;
		if (jsonBeacon.has("proximityUUID")) {
			proximityUUID = UUID.fromString(jsonBeacon.getString("proximityUUID"));
		}
		
		return new Beacon(proximityUUID, macAddress, major, minor, measuredPower, rssi);
	}
}