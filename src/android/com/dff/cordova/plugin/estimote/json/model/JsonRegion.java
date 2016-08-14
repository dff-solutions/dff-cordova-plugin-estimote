package com.dff.cordova.plugin.estimote.json.model;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.estimote.sdk.Region;

public class JsonRegion {
	public static JSONObject toJson(Region region)  throws JSONException {
		JSONObject jsonThread = new JSONObject();
		
		jsonThread.put("identifier", region.getIdentifier());
		jsonThread.put("major", region.getMajor());
		jsonThread.put("minor", region.getMinor());
		jsonThread.put("proximityUUID", region.getProximityUUID());
		
		return jsonThread;
	}
	
	public static Region fromJson(JSONObject jsonRegion) throws JSONException {
		String identifier = null;
		UUID proximityUUID = null;
		Integer major = null;
		Integer minor = null;
		
		if (jsonRegion.has("identifier")){
			identifier = jsonRegion.getString("identifier");
		}		
		
		if (jsonRegion.has("major")){
			major = new Integer(jsonRegion.getInt("major"));
		}		
		
		if (jsonRegion.has("minor")){
			minor = new Integer(jsonRegion.getInt("minor"));
		}		
		
		if (jsonRegion.has("proximityUUID")) {
			proximityUUID = UUID.fromString(jsonRegion.getString("proximityUUID"));
		}
		
		return new Region(identifier, proximityUUID, major, minor);		
	}
}
