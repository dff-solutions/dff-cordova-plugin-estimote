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
		jsonBeacon.put("uuid", beacon.getProximityUUID());
		
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
		UUID uuid = null;
		
		if (!jsonBeacon.isNull("major")){
			major = jsonBeacon.getInt("major");
		}
		
		
		if (!jsonBeacon.isNull("measuredPower")){
			measuredPower = jsonBeacon.getInt("measuredPower");
		}
		
		
		if (!jsonBeacon.isNull("minor")){
			minor = jsonBeacon.getInt("minor");
		}
		
		
		if (!jsonBeacon.isNull("rssi")){
			rssi = jsonBeacon.getInt("rssi");
		}
		
		
		if (!jsonBeacon.isNull("macAddress")) {
			macAddress = MacAddress.fromString(jsonBeacon.getString("macAddress"));
		}
		
		
		if (!jsonBeacon.isNull("uuid")) {
			uuid = UUID.fromString(jsonBeacon.getString("uuid"));
		}
		
		return new Beacon(uuid, macAddress, major, minor, measuredPower, rssi);
	}
}