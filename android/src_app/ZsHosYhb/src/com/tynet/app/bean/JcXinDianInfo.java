package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcXinDianInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private String zyh;// String 住院号
	private String name;// String 姓名
	private String sex;// String 性别
	private String age;// String 年龄
	private String sfzh;// String 身份证号
	private String bed;// String 床号
	private String lb;// String 类别
	private String bq;// String 病区
	private String kdks;// String 开单科室
	private String jcys;// String 检查医生
	private String jcrq;// String 检查日期
	private String bgzt;// String 报告状态
	private String guid;// String 唯一标示
	private String jcdl;// String 检查大类
	private String jcxm;// String 检查项目
	private String sqdh;// String 申请单号
	private String zd;// String 诊断


	public String getZyh() {
		return zyh;
	}


	public void setZyh(String zyh) {
		this.zyh = zyh;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSfzh() {
		return sfzh;
	}


	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}


	public String getBed() {
		return bed;
	}


	public void setBed(String bed) {
		this.bed = bed;
	}


	public String getLb() {
		return lb;
	}


	public void setLb(String lb) {
		this.lb = lb;
	}


	public String getBq() {
		return bq;
	}


	public void setBq(String bq) {
		this.bq = bq;
	}


	public String getKdks() {
		return kdks;
	}


	public void setKdks(String kdks) {
		this.kdks = kdks;
	}


	public String getJcys() {
		return jcys;
	}


	public void setJcys(String jcys) {
		this.jcys = jcys;
	}


	public String getJcrq() {
		return jcrq;
	}


	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}


	public String getBgzt() {
		return bgzt;
	}


	public void setBgzt(String bgzt) {
		this.bgzt = bgzt;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public String getJcdl() {
		return jcdl;
	}


	public void setJcdl(String jcdl) {
		this.jcdl = jcdl;
	}


	public String getJcxm() {
		return jcxm;
	}


	public void setJcxm(String jcxm) {
		this.jcxm = jcxm;
	}


	public String getSqdh() {
		return sqdh;
	}


	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}


	public String getZd() {
		return zd;
	}


	public void setZd(String zd) {
		this.zd = zd;
	}


	public static JcXinDianInfo parse(JSONObject jsonObject) throws JSONException{
		JcXinDianInfo jcObj = new JcXinDianInfo();
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
			jcObj.setZyh(obj.has("zyh")?obj.getString("zyh"):"");
			jcObj.setName(obj.has("name")?obj.getString("name"):"");
			jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
			jcObj.setAge(obj.has("age")?obj.getString("age"):"");
			jcObj.setSfzh(obj.has("sfzh")?obj.getString("sfzh"):"");
			jcObj.setBed(obj.has("bed")?obj.getString("bed"):"");
			jcObj.setLb(obj.has("lb")?obj.getString("lb"):"");
			jcObj.setBq(obj.has("bq")?obj.getString("bq"):"");
			jcObj.setKdks(obj.has("kdks")?obj.getString("kdks"):"");
			jcObj.setJcys(obj.has("jcys")?obj.getString("jcys"):"");
			jcObj.setJcrq(obj.has("jcrq")?obj.getString("jcrq"):"");
			jcObj.setBgzt(obj.has("bgzt")?obj.getString("bgzt"):"");
			jcObj.setGuid(obj.has("guid")?obj.getString("guid"):"");
			jcObj.setJcdl(obj.has("jcdl")?obj.getString("jcdl"):"");
			jcObj.setJcxm(obj.has("jcxm")?obj.getString("jcxm"):"");
			jcObj.setSqdh(obj.has("sqdh")?obj.getString("sqdh"):"");
			jcObj.setZd(obj.has("zd")?obj.getString("zd"):"");

		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}
}
