package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcXinDianList extends Base{
private static final long serialVersionUID = 1L;	
	private List<JcXinDian> m_list = new ArrayList<JcXinDian>();
	
	public List<JcXinDian> getJclbList() {
		return m_list;
	}

	public static JcXinDianList parse(JSONObject jsonObject) throws JSONException {
		JcXinDianList objList = new JcXinDianList();
		JcXinDian jcObj = null;
		String code = jsonObject.getString("code");
		System.out.println("jsonObject");
		System.out.println(jsonObject);
		if(code.equals("0")){
			objList.setSuccess(true);
			JSONArray j = jsonObject.getJSONArray("list");
			for (int i = 0; i < j.length(); i++) {
				JSONObject obj = j.getJSONObject(i);
				jcObj = new JcXinDian();				
				jcObj.setJcrq(obj.has("jcrq")?obj.getString("jcrq"):"");
				jcObj.setName(obj.has("name")?obj.getString("name"):"");
				jcObj.setZyh(obj.has("zyh")?obj.getString("zyh"):"");
				jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
				jcObj.setAge(obj.has("age")?obj.getString("age"):"");
				jcObj.setBed(obj.has("bed")?obj.getString("bed"):"");
				jcObj.setBq(obj.has("bq")?obj.getString("bq"):"");
				jcObj.setJcxm(obj.has("jcxm")?obj.getString("jcxm"):"");
				jcObj.setKdks(obj.has("kdks")?obj.getString("kdks"):"");
				jcObj.setGuid(obj.has("guid")?obj.getString("guid"):"");
				objList.getJclbList().add(jcObj);
			}
		}else{
			objList.setMessage(jsonObject.getString("message"));	
		}
        return objList;       
	}
}
