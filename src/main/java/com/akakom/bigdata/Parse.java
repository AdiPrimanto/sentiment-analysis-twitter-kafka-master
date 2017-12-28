package com.akakom.bigdata;


import org.json.JSONArray;
import org.json.JSONObject;

public class Parse {
	public static void main(String[] args) {
		
		JSONArray hobi = new JSONArray();
		hobi.put("ngoding");
		hobi.put("ngegame");
		
		
		JSONObject obj = new JSONObject();
		obj.put("nama", "indra ");
		obj.put("umur", 14);
		obj.put("hobi", hobi);
		
		JSONObject parent = new JSONObject();
		parent.put("nama", "usfi ");
		parent.put("umur", 15);
		
		obj.put("parent", parent);
		
		System.out.println(obj);
		System.out.println("==================");
		System.out.println("Nama saya "+obj.getString("nama"));
	}

}
