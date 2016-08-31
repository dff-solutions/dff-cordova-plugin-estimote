package com.dff.cordova.plugin.estimote.json.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.estimote.sdk.cloud.model.BroadcastingPower;

public class JsonBroadcastingPower {
	public static JSONObject toJson(BroadcastingPower broadcastingPower) throws JSONException {
		JSONObject jsonBroadcastingPower = new JSONObject();
		
		if (broadcastingPower != null) {
			jsonBroadcastingPower.put("powerInDbm", broadcastingPower.powerInDbm);
			jsonBroadcastingPower.put("ordinal", broadcastingPower.ordinal());
			jsonBroadcastingPower.put("name", broadcastingPower.name());
		}
		
		return jsonBroadcastingPower;
	}
}
