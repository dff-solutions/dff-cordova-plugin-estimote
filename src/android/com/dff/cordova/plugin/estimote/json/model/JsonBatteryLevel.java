package com.dff.cordova.plugin.estimote.json.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.estimote.sdk.Nearable;

public class JsonBatteryLevel {
	public static JSONObject toJson(Nearable.BatteryLevel batteryLevel) throws JSONException {
		JSONObject jsonBatteryLevel = new JSONObject();
		
		if (batteryLevel != null) {
			jsonBatteryLevel.put("voltage", batteryLevel.voltage);
			jsonBatteryLevel.put("name", batteryLevel.name());
			jsonBatteryLevel.put("ordinal", batteryLevel.ordinal());
		}
		
		return jsonBatteryLevel;
	}
}
