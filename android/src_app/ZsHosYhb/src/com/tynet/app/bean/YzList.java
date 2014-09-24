package com.tynet.app.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tynet.app.AppException;

public class YzList extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Yzlb> YzlbList = new ArrayList<Yzlb>();
	
	public List<Yzlb> getYzlbList() {
		return YzlbList;
	}

	public static YzList parse(JSONObject jsonObject) throws JSONException {
		YzList yzlbList = new YzList();
		Yzlb yzlb = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			yzlbList.setSuccess(true);
			JSONObject o = jsonObject.getJSONObject("data");
			JSONArray j = o.getJSONArray("list");
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject yzListJSON = j.getJSONObject(i);
				yzlb = new Yzlb();
				yzlb.setFrequCode(yzListJSON.has("frequCode")?yzListJSON.getString("frequCode"):"");
				yzlb.setGroupNo(yzListJSON.has("groupNo")?yzListJSON.getString("groupNo"):"");
				yzlb.setOrderNo(yzListJSON.has("orderNo")?yzListJSON.getString("orderNo"):"");
				yzlb.setPhysicianName(yzListJSON.has("physicianName")?yzListJSON.getString("physicianName"):"");
				yzlb.setStartTime(yzListJSON.has("startTime")?yzListJSON.getString("startTime"):"");
				yzlb.setSupplyName(yzListJSON.has("supplyName")?yzListJSON.getString("supplyName"):"");
				yzlb.setYznr(yzListJSON.has("yznr")?yzListJSON.getString("yznr"):"");
				yzlb.setPerformanceTime(yzListJSON.has("performanceTime")?yzListJSON.getString("performanceTime"):"");
				yzlb.setStopTime(yzListJSON.has("stopTime")?yzListJSON.getString("stopTime"):"");
				yzlb.setParentName(yzListJSON.has("relationTel")?yzListJSON.getString("parentName"):"");
				
				yzlbList.getYzlbList().add(yzlb);				
			}
		}else{
			yzlbList.setMessage(jsonObject.getString("message"));	
		}
        return yzlbList;       
	}
}
