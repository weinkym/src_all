package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcBChaoList extends Base{
private static final long serialVersionUID = 1L;
	
	private List<JcBChao> jclbList = new ArrayList<JcBChao>();
	
	public List<JcBChao> getJclbList() {
		return jclbList;
	}

	public static JcBChaoList parse(JSONObject jsonObject) throws JSONException {
		JcBChaoList lsJclbList = new JcBChaoList();
		JcBChao tempLb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			lsJclbList.setSuccess(true);
			JSONArray j = jsonObject.getJSONArray("list");
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject jyListrJSON = j.getJSONObject(i);
				tempLb = new JcBChao();
				tempLb.setZyh(jyListrJSON.has("zyh")?jyListrJSON.getString("zyh"):"");
				tempLb.setName(jyListrJSON.has("name")?jyListrJSON.getString("name"):"");
				tempLb.setSex(jyListrJSON.has("sex")?jyListrJSON.getString("sex"):"");
				tempLb.setNl(jyListrJSON.has("nl")?jyListrJSON.getString("nl"):"");
				tempLb.setSqks(jyListrJSON.has("sqks")?jyListrJSON.getString("sqks"):"");
				tempLb.setBgsj(jyListrJSON.has("bgsj")?jyListrJSON.getString("bgsj"):"");
				tempLb.setBbys(jyListrJSON.has("bbys")?jyListrJSON.getString("bbys"):"");
				tempLb.setJcxm(jyListrJSON.has("jcxm")?jyListrJSON.getString("jcxm"):"");
				tempLb.setLsh(jyListrJSON.has("lsh")?jyListrJSON.getString("lsh"):"");
				lsJclbList.getJclbList().add(tempLb);				
			}
		}else{
			lsJclbList.setMessage(jsonObject.getString("message"));	
		}
        return lsJclbList;       
	}
}
