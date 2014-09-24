package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;


public class Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	protected String cacheKey;
	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static Base parse(JSONObject jsonObject) throws JSONException{
		Base base = new Base();
		base.setCode(jsonObject.getString("code"));
		if(jsonObject.getString("code").equals("0")){
			base.setSuccess(true);
		}else{
			base.setSuccess(false);
		}
		base.setMessage(jsonObject.getString("message"));
		return base;
	}
}

