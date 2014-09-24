package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcNeiJingList extends Base{
private static final long serialVersionUID = 1L;	
	private List<JcNeiJing> m_list = new ArrayList<JcNeiJing>();
	
	public List<JcNeiJing> getJclbList() {
		return m_list;
	}

	public static JcNeiJingList parse(JSONObject jsonObject) throws JSONException {
		JcNeiJingList obj = new JcNeiJingList();
		JcNeiJing tempLb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			obj.setSuccess(true);
//			JSONObject o = jsonObject.getJSONObject("data");
//			JSONArray j = o.getJSONArray("list");
			JSONArray j = jsonObject.getJSONArray("list");
			for (int i = 0; i < j.length(); i++) {
				JSONObject jyListrJSON = j.getJSONObject(i);
				tempLb = new JcNeiJing();
				tempLb.setJcrq(jyListrJSON.has("jcrq")?jyListrJSON.getString("jcrq"):"");
				tempLb.setName(jyListrJSON.has("name")?jyListrJSON.getString("name"):"");
				tempLb.setZyh(jyListrJSON.has("zyh")?jyListrJSON.getString("zyh"):"");
				tempLb.setJcxm(jyListrJSON.has("jcxm")?jyListrJSON.getString("jcxm"):"");
				tempLb.setSqys(jyListrJSON.has("sqys")?jyListrJSON.getString("sqys"):"");
				tempLb.setSqks(jyListrJSON.has("sqks")?jyListrJSON.getString("sqks"):"");
				tempLb.setLsh(jyListrJSON.has("lsh")?jyListrJSON.getString("lsh"):"");
				tempLb.setSex(jyListrJSON.has("sex")?jyListrJSON.getString("sex"):"");
				tempLb.setNl(jyListrJSON.has("nl")?jyListrJSON.getString("nl"):"");
				obj.getJclbList().add(tempLb);				
			}
		}else{
			obj.setMessage(jsonObject.getString("message"));	
		}
        return obj;       
	}
}
