package com.tynet.app.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tynet.app.AppException;

public class LsYzList extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LsYzlb> LsYzlbList = new ArrayList<LsYzlb>();
	
	public List<LsYzlb> getLsYzlbList() {
		return LsYzlbList;
	}

	public static LsYzList parse(JSONObject jsonObject) throws JSONException {
		LsYzList lsYzlbList = new LsYzList();
		LsYzlb lsYzlb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			lsYzlbList.setSuccess(true);
			JSONObject o = jsonObject.getJSONObject("data");
			JSONArray j = o.getJSONArray("list");
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject yzListJSON = j.getJSONObject(i);
				lsYzlb = new LsYzlb();
				lsYzlb.setFrequCode(yzListJSON.has("frequCode")?yzListJSON.getString("frequCode"):"");
				lsYzlb.setGroupNo(yzListJSON.has("groupNo")?yzListJSON.getString("groupNo"):"");
				lsYzlb.setOrderNo(yzListJSON.has("orderNo")?yzListJSON.getString("orderNo"):"");
				lsYzlb.setPhysicianName(yzListJSON.has("physicianName")?yzListJSON.getString("physicianName"):"");
				lsYzlb.setStartTime(yzListJSON.has("startTime")?yzListJSON.getString("startTime"):"");
				lsYzlb.setSupplyName(yzListJSON.has("supplyName")?yzListJSON.getString("supplyName"):"");
				lsYzlb.setYznr(yzListJSON.has("yznr")?yzListJSON.getString("yznr"):"");
				lsYzlb.setZxTime(yzListJSON.has("zxTime")?yzListJSON.getString("zxTime"):"");
				lsYzlb.setZxr(yzListJSON.has("zxr")?yzListJSON.getString("zxr"):"");
				lsYzlb.setParentName(yzListJSON.has("relationTel")?yzListJSON.getString("parentName"):"");
				
				lsYzlbList.getLsYzlbList().add(lsYzlb);				
			}
		}else{
			lsYzlbList.setMessage(jsonObject.getString("message"));	
		}
        return lsYzlbList;       
	}
}
