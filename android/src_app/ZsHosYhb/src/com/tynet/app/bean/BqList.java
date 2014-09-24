package com.tynet.app.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tynet.app.AppException;

public class BqList extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Bq> BqList = new ArrayList<Bq>();
	

	public List<Bq> getBqList() {
		return BqList;
	}


	public static BqList parse(JSONObject jsonObject) throws JSONException {
		BqList bqList = new BqList();
		Bq bq = null;
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			bqList.setSuccess(true);
			JSONArray j = jsonObject.getJSONArray("bqlist");
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject bqJSON = j.getJSONObject(i);
				bq = new Bq();
				bq.setBqdm(bqJSON.getString("bqdm"));
				bq.setBqmc(bqJSON.getString("bqmc"));
				bq.setSrm1(bqJSON.has("srm3")?bqJSON.getString("srm1"):"");
				bq.setSrm3(bqJSON.has("srm3")?bqJSON.getString("srm3"):"");

				bqList.getBqList().add(bq);			
			}
		}else{
			bqList.setMessage(jsonObject.getString("message"));	
		}
        return bqList;       
	}
}
