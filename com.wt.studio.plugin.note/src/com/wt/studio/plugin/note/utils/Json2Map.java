package com.wt.studio.plugin.note.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Json2Map
{
	 
	 public static JSONArray ParseJson(String jsonString) throws JSONException,ParseException {
		

		      /*  JSONObject jsonObject = new JSONObject(jsonString);
		        Map result = new HashMap();
		        Iterator iterator = jsonObject.keys();
		        String key = null;
		        String value = null;
		        
		        while (iterator.hasNext()) {

		            key = (String) iterator.next();
		            value = jsonObject.getString(key);
		            result.put(key, value);
		        }*/
		   
		    
		    JSONObject jo = new JSONObject(jsonString);
	        JSONArray ja = jo.getJSONArray("topics");
	        return ja;
	        //System.out.println(ja.length());
	        //System.out.println("topicid: " + ja.getJSONObject(0).getString("topicid")
	                //+ " title: " + ja.getJSONObject(0).getString("title") );

		    }
	 
	 public static JSONArray ParseJson1(String jsonString) {
		 JSONObject jo1;
		try {
			jo1 = new JSONObject(jsonString);
			JSONArray ja1 = jo1.getJSONArray("topiccontents");
			 return ja1;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		 
	 }
          

}

	 

