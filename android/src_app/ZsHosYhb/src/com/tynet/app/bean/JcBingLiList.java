package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcBingLiList extends Base{
private static final long serialVersionUID = 1L;	
	private List<JcBingLi> m_list = new ArrayList<JcBingLi>();
	
	public List<JcBingLi> getJclbList() {
		return m_list;
	}

	public static JcBingLiList parse(JSONObject jsonObject) throws JSONException {
		JcBingLiList jcObjList = new JcBingLiList();
		JcBingLi jcObj = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			jcObjList.setSuccess(true);
//			JSONObject o = jsonObject.getJSONObject("data");
//			JSONArray j = o.getJSONArray("list");
			JSONArray j = jsonObject.getJSONArray("list");
			for (int i = 0; i < j.length(); i++) {
				JSONObject obj = j.getJSONObject(i);
				jcObj = new JcBingLi();
				jcObj.setBglb(obj.has("bglb")?obj.getString("bglb"):"");
				jcObj.setName(obj.has("name")?obj.getString("name"):"");
				jcObj.setZyh(obj.has("zyh")?obj.getString("zyh"):"");
				jcObj.setBq(obj.has("bq")?obj.getString("bq"):"");
				jcObj.setBgrq(obj.has("bgrq")?obj.getString("bgrq"):"");
				jcObj.setBlh(obj.has("blh")?obj.getString("blh"):"");
				jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
				jcObj.setNl(obj.has("nl")?obj.getString("nl"):"");
				jcObjList.getJclbList().add(jcObj);				
			}
		}else{
			jcObjList.setMessage(jsonObject.getString("message"));	
		}
        return jcObjList;       
	}
}
