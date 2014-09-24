package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcFangSheList extends Base{
private static final long serialVersionUID = 1L;
	
	private List<JcFangShe> jcfangsheList = new ArrayList<JcFangShe>();
	
	public List<JcFangShe> getJclbList() {
		return jcfangsheList;
	}

	public static JcFangSheList parse(JSONObject jsonObject) throws JSONException {
		JcFangSheList objList = new JcFangSheList();
		JcFangShe tempLb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			objList.setSuccess(true);			
//			JSONObject o = jsonObject.getJSONObject("data");
//			JSONArray j = o.getJSONArray("list");
			JSONArray j = jsonObject.getJSONArray("list");			
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject jyListrJSON = j.getJSONObject(i);
				tempLb = new JcFangShe();
				tempLb.setApprovedate(jyListrJSON.has("approvedate")?jyListrJSON.getString("approvedate"):"");
				tempLb.setExamItemName(jyListrJSON.has("examItemName")?jyListrJSON.getString("examItemName"):"");
				tempLb.setExamname(jyListrJSON.has("examname")?jyListrJSON.getString("examname"):"");
				tempLb.setClinicId(jyListrJSON.has("clinicId")?jyListrJSON.getString("clinicId"):"");
				tempLb.setName(jyListrJSON.has("name")?jyListrJSON.getString("name"):"");
				tempLb.setDept_name(jyListrJSON.has("dept_name")?jyListrJSON.getString("dept_name"):"");
				tempLb.setCheckNumber(jyListrJSON.has("checkNumber")?jyListrJSON.getString("checkNumber"):"");
				tempLb.setGender(jyListrJSON.has("gender")?jyListrJSON.getString("gender"):"");
				tempLb.setAge(jyListrJSON.has("age")?jyListrJSON.getString("age"):"");

				objList.getJclbList().add(tempLb);				
			}
		}else{
			objList.setMessage(jsonObject.getString("message"));	
		}
        return objList;       
	}
}

