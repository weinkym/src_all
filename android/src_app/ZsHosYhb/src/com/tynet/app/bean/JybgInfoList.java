package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JybgInfoList extends Base{
private static final long serialVersionUID = 1L;
	
	private List<JybgInfo> jybgInfoList = new ArrayList<JybgInfo>();
	
	public List<JybgInfo> getJybgInfoList() {
		return jybgInfoList;
	}

	public static JybgInfoList parse(JSONObject jsonObject) throws JSONException {
		JybgInfoList objList = new JybgInfoList();
		JybgInfo tempLb = null;
		String code = jsonObject.getString("code");
		System.out.println("TTTTTTTTT<JybgInfoList>");
		System.out.println(jsonObject);
		
		if(code.equals("0")){
			objList.setSuccess(true);
//			JSONObject o = jsonObject.getJSONObject("data");
//			JSONArray j = o.getJSONArray("list");
			JSONArray j = jsonObject.getJSONArray("list");
			System.out.println("TTTTTTTTT<j.length();>");
			System.out.println(j.length());
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject obj = j.getJSONObject(i);
				System.out.println("TTTTTTTTT<obj>");
				System.out.println(obj);
				
				tempLb = new JybgInfo();
				tempLb.setEnglishab(obj.has("englishab")?obj.getString("englishab"):"");
				tempLb.setChinesename(obj.has("chinesename")?obj.getString("chinesename"):"");
				tempLb.setTestresult(obj.has("testresult")?obj.getString("testresult"):"");
				tempLb.setRange(obj.has("range")?obj.getString("range"):"");
				tempLb.setUnit(obj.has("unit")?obj.getString("unit"):"");
				tempLb.setResultflag(obj.has("resultflag")?obj.getString("resultflag"):"");
				tempLb.setTestid(obj.has("testid")?obj.getString("testid"):"");
				objList.getJybgInfoList().add(tempLb);				
			}
		}else{
			objList.setMessage(jsonObject.getString("message"));	
		}
        return objList;       
	}
}
