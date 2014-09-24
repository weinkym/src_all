package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcBChaoInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private String zyh;// String 住院号
	private String name;// String 姓名
	private String sex;// String 性别
	private String nl;// String 年龄
	private String sqks;// String 申请科室
	private String bgsj;// String 报告时间
	private String bbys;// String 报告医生
	private String jcxm;// String 检查项目
	private String lsh;// String 流水号
	private String bed;// String 床号
	private String sqys;// String 申请医生
	private String jcsj;// String 检查所见
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

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getSqks() {
		return sqks;
	}

	public void setSqks(String sqks) {
		this.sqks = sqks;
	}

	public String getBgsj() {
		return bgsj;
	}

	public void setBgsj(String bgsj) {
		this.bgsj = bgsj;
	}

	public String getBbys() {
		return bbys;
	}

	public void setBbys(String bbys) {
		this.bbys = bbys;
	}

	public String getJcxm() {
		return jcxm;
	}

	public void setJcxm(String jcxm) {
		this.jcxm = jcxm;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getSqys() {
		return sqys;
	}

	public void setSqys(String sqys) {
		this.sqys = sqys;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public static JcBChaoInfo parse(JSONObject jsonObject) throws JSONException{
		JcBChaoInfo jcObj = new JcBChaoInfo();
		System.out.println("jsonObject");
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
			System.out.println(obj);
			jcObj.setZyh(obj.has("zyh")?obj.getString("zyh"):"");
			jcObj.setName(obj.has("name")?obj.getString("name"):"");
			jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
			jcObj.setNl(obj.has("nl")?obj.getString("nl"):"");
			jcObj.setSqks(obj.has("sqks")?obj.getString("sqks"):"");
			jcObj.setBgsj(obj.has("bgsj")?obj.getString("bgsj"):"");
			jcObj.setBbys(obj.has("bbys")?obj.getString("bbys"):"");
			jcObj.setJcxm(obj.has("jcxm")?obj.getString("jcxm"):"");
			jcObj.setLsh(obj.has("lsh")?obj.getString("lsh"):"");
			jcObj.setBed(obj.has("bed")?obj.getString("bed"):"");
			jcObj.setSqys(obj.has("sqys")?obj.getString("sqys"):"");
			jcObj.setJcsj(obj.has("jcsj")?obj.getString("jcsj"):"");
			jcObj.setZd(obj.has("zd")?obj.getString("zd"):"");

		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}
}
