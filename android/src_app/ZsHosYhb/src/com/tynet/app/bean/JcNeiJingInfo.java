package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcNeiJingInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jcrq;// String 检查日期
	private String name;// String 姓名
	private String zyh;// String --住院号
	private String jcxm;// String 检查项目
	private String sqys;// String -申请医生
	private String sqks;// String 申请科室
	private String lsh;// String --内镜系统内部的唯一标识   流水号
	private String sex;// String 性别
	private String nl;// String 年龄
	private String yqmc;// String -仪器名称
	private String jcys;// String -检查医生
	private String zdms;// String --诊断描述
	private String njzd;// String --内镜诊断
	private String hj;// String --活检
	private String ysjy;// String    --医生建议
//	private String lsh;// String --内镜系统内部的唯一标识   流水号

	

	public static JcNeiJingInfo parse(JSONObject jsonObject) throws JSONException{
		JcNeiJingInfo jcObj = new JcNeiJingInfo();
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTT<12333>");
		System.out.println(jsonObject);
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
			jcObj.setJcrq(obj.has("jcrq")?obj.getString("jcrq"):"");
			jcObj.setName(obj.has("name")?obj.getString("name"):"");
			jcObj.setZyh(obj.has("zyh")?obj.getString("zyh"):"");
			jcObj.setJcxm(obj.has("jcxm")?obj.getString("jcxm"):"");
			jcObj.setSqys(obj.has("sqys")?obj.getString("sqys"):"");
			jcObj.setSqks(obj.has("sqks")?obj.getString("sqks"):"");
			jcObj.setLsh(obj.has("lsh")?obj.getString("lsh"):"");
			jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
			jcObj.setNl(obj.has("nl")?obj.getString("nl"):"");
			jcObj.setYqmc(obj.has("yqmc")?obj.getString("yqmc"):"");
			jcObj.setJcys(obj.has("jcys")?obj.getString("jcys"):"");
			jcObj.setZdms(obj.has("zdms")?obj.getString("zdms"):"");
			jcObj.setNjzd(obj.has("njzd")?obj.getString("njzd"):"");
			jcObj.setHj(obj.has("hj")?obj.getString("hj"):"");
			jcObj.setYsjy(obj.has("ysjy")?obj.getString("ysjy"):"");
		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}


	public String getJcrq() {
		return jcrq;
	}



	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getZyh() {
		return zyh;
	}



	public void setZyh(String zyh) {
		this.zyh = zyh;
	}



	public String getJcxm() {
		return jcxm;
	}



	public void setJcxm(String jcxm) {
		this.jcxm = jcxm;
	}



	public String getSqys() {
		return sqys;
	}



	public void setSqys(String sqys) {
		this.sqys = sqys;
	}



	public String getSqks() {
		return sqks;
	}



	public void setSqks(String sqks) {
		this.sqks = sqks;
	}



	public String getLsh() {
		return lsh;
	}



	public void setLsh(String lsh) {
		this.lsh = lsh;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getNl() {
		return nl;
	}



	public void setNl(String nl) {
		this.nl = nl;
	}



	public String getYqmc() {
		return yqmc;
	}



	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}



	public String getJcys() {
		return jcys;
	}



	public void setJcys(String jcys) {
		this.jcys = jcys;
	}



	public String getZdms() {
		return zdms;
	}



	public void setZdms(String zdms) {
		this.zdms = zdms;
	}



	public String getNjzd() {
		return njzd;
	}



	public void setNjzd(String njzd) {
		this.njzd = njzd;
	}



	public String getHj() {
		return hj;
	}



	public void setHj(String hj) {
		this.hj = hj;
	}



	public String getYsjy() {
		return ysjy;
	}



	public void setYsjy(String ysjy) {
		this.ysjy = ysjy;
	}
}
