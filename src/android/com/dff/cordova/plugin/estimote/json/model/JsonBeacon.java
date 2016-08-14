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
		JSONObject jsonBeacon = new JSONObject();
		
		jsonBeacon.put("major", beacon.getMajor());
		jsonBeacon.put("measuredPower", beacon.getMeasuredPower());
		jsonBeacon.put("minor", beacon.getMinor());
		jsonBeacon.put("rssi", beacon.getRssi());
		jsonBeacon.put("macAddress", beacon.getMacAddress());
		jsonBeacon.put("proximityUUID", beacon.getProximityUUID());
		
		return jsonBeacon;
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
		int major = -1;
		int measuredPower = -1;
		int minor = -1;
		int rssi = -1;
		MacAddress macAddress = null;
		UUID proximityUUID = null;
		
		if (jsonBeacon.has("major")){
			major = jsonBeacon.getInt("major");
		}
		
		
		if (jsonBeacon.has("measuredPower")){
			measuredPower = jsonBeacon.getInt("measuredPower");
		}
		
		
		if (jsonBeacon.has("minor")){
			minor = jsonBeacon.getInt("minor");
		}
		
		
		if (jsonBeacon.has("rssi")){
			rssi = jsonBeacon.getInt("rssi");
		}
		
		
		if (jsonBeacon.has("macAddress")) {
			macAddress = MacAddress.fromString(jsonBeacon.getString("macAddress"));
		}
		
		
		if (jsonBeacon.has("proximityUUID")) {
			proximityUUID = UUID.fromString(jsonBeacon.getString("proximityUUID"));
		}
		
		return new Beacon(proximityUUID, macAddress, major, minor, measuredPower, rssi);
	}
}