package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TongXunLuInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String contactId;// String 成员ID
	private String userName;// String 姓名
	private String userJob;// String -职务
	private String mobile;// String 手机号码
	private String shortMobile;// String 手机短号
	private String phone;// String 座机号码（外线）
	private String shortPhone;// String 内线号码（内线）
	private String smallMobil;// String 小灵通
	private String homePhone;// String 宅电
	private String groupType;// String 分组（0-科室。1-个人）
	private String platHosId;// String 医院编号
	private String platDocId;// String 关联平台医生编号
	private String remarks;// String 备注
	private String qq;// String qq
	private String email;// String 邮箱
	private String deptName;// String 科室名称
	private String showNo;// String 排序（升序排）

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getShortMobile() {
		return shortMobile;
	}

	public void setShortMobile(String shortMobile) {
		this.shortMobile = shortMobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShortPhone() {
		return shortPhone;
	}

	public void setShortPhone(String shortPhone) {
		this.shortPhone = shortPhone;
	}

	public String getSmallMobil() {
		return smallMobil;
	}

	public void setSmallMobil(String smallMobil) {
		this.smallMobil = smallMobil;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getPlatHosId() {
		return platHosId;
	}

	public void setPlatHosId(String platHosId) {
		this.platHosId = platHosId;
	}

	public String getPlatDocId() {
		return platDocId;
	}

	public void setPlatDocId(String platDocId) {
		this.platDocId = platDocId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public static TongXunLuInfo parse(JSONObject jsonObject) throws JSONException{
		TongXunLuInfo jcObj = new TongXunLuInfo();
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTT<12333>");
		System.out.println(jsonObject);
		String code = jsonObject.getString("code");
		if(code.equals("0")){			
//			JSONArray jArray = jsonObject.getJSONArray("data");
//			if(jArray.length() <= 0)
//			{
//				jcObj.setMessage(jsonObject.getString("message"));
//				return jcObj;
//			}
			JSONObject dObj = jsonObject.getJSONObject("data");
			JSONObject obj = dObj.getJSONObject("info");
			
			jcObj.setSuccess(true);
//			JSONObject obj = jArray.getJSONObject(0);			
			jcObj.setContactId(obj.has("contactId")?obj.getString("contactId"):"");
			jcObj.setUserName(obj.has("userName")?obj.getString("userName"):"");
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
		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}
}
