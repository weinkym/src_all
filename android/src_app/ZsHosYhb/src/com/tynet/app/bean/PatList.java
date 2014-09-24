package com.tynet.app.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tynet.app.AppException;

public class PatList extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GbPat> GbPatList = new ArrayList<GbPat>();
	
	public List<GbPat> getGbPatList() {
		return GbPatList;
	}

	public static PatList parse(JSONObject jsonObject) throws JSONException {
		PatList gbPatList = new PatList();
		GbPat gbPat = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			gbPatList.setSuccess(true);
			JSONArray j = jsonObject.getJSONArray("patlist");
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject gbPatJSON = j.getJSONObject(i);
				gbPat = new GbPat();
				gbPat.setPatientNo(gbPatJSON.has("patientNo")?gbPatJSON.getString("patientNo"):"");
				gbPat.setJsNo(gbPatJSON.has("jsNo")?gbPatJSON.getString("jsNo"):"");
				gbPat.setName(gbPatJSON.has("name")?gbPatJSON.getString("name"):"");
				gbPat.setBah(gbPatJSON.has("bah")?gbPatJSON.getString("bah"):"");
				gbPat.setNl(gbPatJSON.has("nl")?gbPatJSON.getString("nl"):"");
				gbPat.setSex(gbPatJSON.has("sex")?gbPatJSON.getString("sex"):"");
				gbPat.setSocialId(gbPatJSON.has("socialId")?gbPatJSON.getString("socialId"):"");
				gbPat.setHomeTel(gbPatJSON.has("homeTel")?gbPatJSON.getString("homeTel"):"");
				gbPat.setEmployerTel(gbPatJSON.has("employerTel")?gbPatJSON.getString("employerTel"):"");
				gbPat.setRelationTel(gbPatJSON.has("relationTel")?gbPatJSON.getString("relationTel"):"");
				gbPat.setHomeAddress(gbPatJSON.has("homeAddress")?gbPatJSON.getString("homeAddress"):"");
				gbPat.setEmployer(gbPatJSON.has("employer")?gbPatJSON.getString("employer"):"");
				gbPat.setBrxz(gbPatJSON.has("brxz")?gbPatJSON.getString("brxz"):"");
				gbPat.setBrlb(gbPatJSON.has("brlb")?gbPatJSON.getString("brlb"):"");
				gbPat.setAdmissDate(gbPatJSON.has("admissDate")?gbPatJSON.getString("admissDate"):"");
				gbPat.setPreoutDate(gbPatJSON.has("preoutDate")?gbPatJSON.getString("preoutDate"):"");
				gbPat.setAdmissKsCode(gbPatJSON.has("admissKsCode")?gbPatJSON.getString("admissKsCode"):"");
				gbPat.setAdmissKsName(gbPatJSON.has("admissKsName")?gbPatJSON.getString("admissKsName"):"");
				gbPat.setCurrKs(gbPatJSON.has("currKs")?gbPatJSON.getString("currKs"):"");
				gbPat.setCurrKsmc(gbPatJSON.has("currKsmc")?gbPatJSON.getString("currKsmc"):"");
				gbPat.setCurrBed(gbPatJSON.has("currBed")?gbPatJSON.getString("currBed"):"");
				gbPat.setDiagName(gbPatJSON.has("diagName")?gbPatJSON.getString("diagName"):"");
				gbPat.setYsCode(gbPatJSON.has("ysCode")?gbPatJSON.getString("ysCode"):"");
				gbPat.setYsName(gbPatJSON.has("ysName")?gbPatJSON.getString("ysName"):"");
				gbPat.setZzys(gbPatJSON.has("zzys")?gbPatJSON.getString("zzys"):"");
				gbPat.setCurrBq(gbPatJSON.has("currBq")?gbPatJSON.getString("currBq"):"");
				gbPat.setBqmc(gbPatJSON.has("bqmc")?gbPatJSON.getString("bqmc"):"");

				gbPatList.getGbPatList().add(gbPat);				
			}
		}else{
			gbPatList.setMessage(jsonObject.getString("message"));	
		}
        return gbPatList;       
	}
}
