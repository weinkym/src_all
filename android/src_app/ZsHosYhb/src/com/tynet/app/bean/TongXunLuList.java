package com.tynet.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tynet.app.api.CharacterParser;

public class TongXunLuList extends Base{
private static final long serialVersionUID = 1L;
	
	private List<TongXunLu> jclbList = new ArrayList<TongXunLu>();	
	public List<TongXunLu> getJclbList() {
		return jclbList;
	}

	public static TongXunLuList parse(JSONObject jsonObject) throws JSONException {
		TongXunLuList lsJclbList = new TongXunLuList();
		TongXunLu jcObj = null;
		System.out.println("TongXunLuList jsonObject");
		System.out.println(jsonObject);
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			lsJclbList.setSuccess(true);
			JSONObject o = jsonObject.getJSONObject("data");
			JSONArray j = o.getJSONArray("list");			
//			JSONArray j = jsonObject.getJSONArray("list");
			System.out.println("length" + j.length());
			CharacterParser characterParser = CharacterParser.getInstance();
			
			for (int i = 0; i < j.length(); i++) {
				JSONObject obj = j.getJSONObject(i);
				System.out.println("obj");
				System.out.println(obj);
				
				jcObj = new TongXunLu();
				String userName = obj.has("userName")?obj.getString("userName"):"";
				jcObj.setUserName(userName);
				String pinyin = characterParser.getSelling(userName);
				String sortString = pinyin.substring(0, 1).toUpperCase();				
				// 正则表达式，判断首字母是否是英文字母
				if(sortString.matches("[A-Z]")){
					jcObj.setSortLetters(sortString.toUpperCase());
				}else{
					jcObj.setSortLetters("#");
				}
				jcObj.setContactId(obj.has("contactId")?obj.getString("contactId"):"");
//				jcObj.setUserName(obj.has("userName")?obj.getString("userName"):"");
				jcObj.setUserJob(obj.has("userJob")?obj.getString("userJob"):"");
				jcObj.setMobile(obj.has("mobile")?obj.getString("mobile"):"");
				jcObj.setShortMobile(obj.has("shortMobile")?obj.getString("shortMobile"):"");
				jcObj.setPhone(obj.has("phone")?obj.getString("phone"):"");
				jcObj.setShortPhone(obj.has("shortPhone")?obj.getString("shortPhone"):"");
				jcObj.setSmallMobil(obj.has("smallMobil")?obj.getString("smallMobil"):"");
				jcObj.setHomePhone(obj.has("homePhone")?obj.getString("homePhone"):"");
				jcObj.setGroupType(obj.has("groupType")?obj.getString("groupType"):"");
				jcObj.setPlatHosId(obj.has("platHosId")?obj.getString("platHosId"):"");
				jcObj.setPlatDocId(obj.has("platDocId")?obj.getString("platDocId"):"");
				jcObj.setRemarks(obj.has("remarks")?obj.getString("remarks"):"");
				jcObj.setQq(obj.has("qq")?obj.getString("qq"):"");
				jcObj.setEmail(obj.has("email")?obj.getString("email"):"");
				jcObj.setDeptName(obj.has("deptName")?obj.getString("deptName"):"");
				jcObj.setShowNo(obj.has("showNo")?obj.getString("showNo"):"");
				lsJclbList.getJclbList().add(jcObj);				
			}
		}else{
			lsJclbList.setMessage(jsonObject.getString("message"));	
		}
        return lsJclbList;       
	}
}
