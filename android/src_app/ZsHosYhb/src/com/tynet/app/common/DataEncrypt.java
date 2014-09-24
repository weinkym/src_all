package com.tynet.app.common;
import org.json.JSONException;
import org.json.JSONObject;

public class DataEncrypt {
	
	private static String encode(JSONObject obj) throws JSONException {
		String key = obj.toString().length()+ obj.getString(DataExchangeConst.METHOD) + obj.getString(DataExchangeConst.SERVICE);
		return key;
	}

	public static String encodeMd5(JSONObject obj) throws JSONException {
		
		String md5 = Md5Util.encode((encode(obj)));
		return md5 + obj.toString().length();
	}
	
}
