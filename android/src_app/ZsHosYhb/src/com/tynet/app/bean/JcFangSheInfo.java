package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcFangSheInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private String approvedate;// String 报告日期
	private String examItemName;// String -检查类别
	private String examname;// String -检查项目
	private String clinicId;// String 住院号
	private String name;// String 姓名
	private String deptName;// String 申请科室
	private String checkNumber;// String 放射科内部唯一判别流水号
	private String gender;// String 性别
	private String age;// String 年龄
	private String bedNo;// String 床号
	private String examdesc;// String -影像表现
	private String examdiagnosis;// String  --诊断
	private String registerTime;// String --检查日期
	private String approvepeople;// String -审核医师

	
	public String getApprovedate() {
		return approvedate;
	}


	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}


	public String getExamItemName() {
		return examItemName;
	}


	public void setExamItemName(String examItemName) {
		this.examItemName = examItemName;
	}


	public String getExamname() {
		return examname;
	}


	public void setExamname(String examname) {
		this.examname = examname;
	}


	public String getClinicId() {
		return clinicId;
	}


	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getCheckNumber() {
		return checkNumber;
	}


	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getBedNo() {
		return bedNo;
	}


	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}


	public String getExamdesc() {
		return examdesc;
	}


	public void setExamdesc(String examdesc) {
		this.examdesc = examdesc;
	}


	public String getExamdiagnosis() {
		return examdiagnosis;
	}


	public void setExamdiagnosis(String examdiagnosis) {
		this.examdiagnosis = examdiagnosis;
	}


	public String getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public String getApprovepeople() {
		return approvepeople;
	}


	public void setApprovepeople(String approvepeople) {
		this.approvepeople = approvepeople;
	}


	public static JcFangSheInfo parse(JSONObject jsonObject) throws JSONException{
		JcFangSheInfo jcObj = new JcFangSheInfo();
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			
			JSONArray jArray = jsonObject.getJSONArray("list");
			if(jArray.length() <= 0)
			{
				jcObj.setMessage(jsonObject.getString("message"));
				return jcObj;
			}
			jcObj.setSuccess(true);
			JSONObject obj = jArray.getJSONObject(0);			
			jcObj.setApprovedate(obj.has("approvedate")?obj.getString("approvedate"):"");
			jcObj.setExamItemName(obj.has("examItemName")?obj.getString("examItemName"):"");
			jcObj.setExamname(obj.has("examname")?obj.getString("examname"):"");
			jcObj.setClinicId(obj.has("clinicId")?obj.getString("clinicId"):"");
			jcObj.setName(obj.has("name")?obj.getString("name"):"");
			jcObj.setDeptName(obj.has("deptName")?obj.getString("deptName"):"");
			jcObj.setCheckNumber(obj.has("checkNumber")?obj.getString("checkNumber"):"");
			jcObj.setGender(obj.has("gender")?obj.getString("gender"):"");
			jcObj.setAge(obj.has("age")?obj.getString("age"):"");
			jcObj.setBedNo(obj.has("bedNo")?obj.getString("bedNo"):"");
			jcObj.setExamdesc(obj.has("examdesc")?obj.getString("examdesc"):"");
			jcObj.setExamdiagnosis(obj.has("examdiagnosis")?obj.getString("examdiagnosis"):"");
			jcObj.setRegisterTime(obj.has("registerTime")?obj.getString("registerTime"):"");
			jcObj.setApprovepeople(obj.has("approvepeople")?obj.getString("approvepeople"):"");
		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}
}
