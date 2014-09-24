package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JybgList extends Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Jybg> JylbList = new ArrayList<Jybg>();
	
	public List<Jybg> getJylbList() {
		return JylbList;
	}

	public static JybgList parse(JSONObject jsonObject) throws JSONException {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTT<12333>");
		System.out.println(jsonObject);
		JybgList lsJylbList = new JybgList();
		Jybg jylb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			System.out.println("T<T001>");
			lsJylbList.setSuccess(true);
			System.out.println("Q<T001>");
//			JSONObject o = jsonObject.getJSONObject("data");
			System.out.println("Q<T002>");

//			JSONArray j = o.getJSONArray("list");
			JSONArray j = jsonObject.getJSONArray("list");
			System.out.println("Q<T003>");

			
			for (int i = 0; i < j.length(); i++) {
				System.out.println("f<T001> i = " + i);

				JSONObject jyListrJSON = j.getJSONObject(i);
				jylb = new Jybg();
				jylb.setPatientid(jyListrJSON.has("patientid")?jyListrJSON.getString("patientid"):"");
				jylb.setPatientname(jyListrJSON.has("patientname")?jyListrJSON.getString("patientname"):"");
				jylb.setDepartBed(jyListrJSON.has("departBed")?jyListrJSON.getString("departBed"):"");
				jylb.setNl(jyListrJSON.has("nl")?jyListrJSON.getString("nl"):"");
				jylb.setZd(jyListrJSON.has("zd")?jyListrJSON.getString("zd"):"");
				jylb.setSqh(jyListrJSON.has("sqh")?jyListrJSON.getString("sqh"):"");
				jylb.setSampletype(jyListrJSON.has("sampletype")?jyListrJSON.getString("sampletype"):"");
				jylb.setExaminaim(jyListrJSON.has("examinaim")?jyListrJSON.getString("examinaim"):"");
				jylb.setRequester(jyListrJSON.has("requester")?jyListrJSON.getString("requester"):"");
				jylb.setExecutetime(jyListrJSON.has("executetime")?jyListrJSON.getString("executetime"):"");
				jylb.setJyz(jyListrJSON.has("jyz")?jyListrJSON.getString("jyz"):"");
				jylb.setJyrq(jyListrJSON.has("jyrq")?jyListrJSON.getString("jyrq"):"");
				jylb.setCheckoperator(jyListrJSON.has("checkoperator")?jyListrJSON.getString("checkoperator"):"");
				jylb.setChecktime(jyListrJSON.has("checktime")?jyListrJSON.getString("checktime"):"");
				jylb.setYbh(jyListrJSON.has("ybh")?jyListrJSON.getString("ybh"):"");
				
				lsJylbList.getJylbList().add(jylb);
				System.out.println("f<end> i = " + i);

			}
		}else{
			System.out.println("T<T002>");

			lsJylbList.setMessage(jsonObject.getString("message"));	
		}
        return lsJylbList;       
	}
}
