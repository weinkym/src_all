package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class EmployeesService extends Base implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String zgid;

    private String pwd;
    
    private String hosId;
    
    private  String jxzyyToken;

	public  String getJxzyyToken() {
		return jxzyyToken;
	}

	public void setJxzyyToken(String jxzyyToken) {
		this.jxzyyToken = jxzyyToken;
	}

	public String getZgid() {
		return zgid;
	}

	public void setZgid(String zgid) {
		this.zgid = zgid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}


	
	public static EmployeesService parse(JSONObject jsonObject) throws JSONException{
		EmployeesService employeesService = new EmployeesService();
		employeesService.setMessage(jsonObject.getString("message"));
		if(jsonObject.getString("code").equals("0")){
			employeesService.setSuccess(true);
			JSONObject data = jsonObject.getJSONObject("data");
			
			employeesService.setJxzyyToken(data.has("jxzyyToken")?data.getString("jxzyyToken"):"");
			
			
		}else{
			employeesService.setSuccess(false);
		}
		return employeesService;
	}

   
}