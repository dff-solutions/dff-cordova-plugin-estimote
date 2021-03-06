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
		jsonThread.put("uuid", region.getProximityUUID());
		
		return jsonThread;
	}
	
	public static Region fromJson(JSONObject jsonRegion) throws JSONException {
		String identifier = null;
		UUID uuid = null;
		Integer major = null;
		Integer minor = null;
		
		if (!jsonRegion.isNull("identifier")){
			identifier = jsonRegion.getString("identifier");
		}		
		
		if (!jsonRegion.isNull("major")){
			major = new Integer(jsonRegion.getInt("major"));
		}		
		
		if (!jsonRegion.isNull("minor")){
			minor = new Integer(jsonRegion.getInt("minor"));
		}		
		
		if (!jsonRegion.isNull("uuid")) {
			uuid = UUID.fromString(jsonRegion.getString("uuid"));
		}
		
		return new Region(identifier, uuid, major, minor);		
	}
}
