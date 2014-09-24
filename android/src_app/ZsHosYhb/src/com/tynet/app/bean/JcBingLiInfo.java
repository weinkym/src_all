package com.tynet.app.bean;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JcBingLiInfo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private String zyh;// String 住院号
	private String bed;// String 床号
	private String name;// String 姓名
	private String sex;// String 性别
	private String nl;// String 年龄
	private String hy;// String 婚姻
	private String bq;// String 病区
	private String bgrq;// String 报告日期
	private String blh;// String 病理号
	private String bglb;// String 报告类别
	private String kdks;// String 开单科室
	private String kdys;// String 开单医生
	private String kdrq;// String 开单日期  
	private String bbmc;// String 标本名称
	private String lczd;// String 临床诊断
	private String blzd;// String 病理诊断
	private String bgys;// String 报告医生
	private String shys;// String 审核医生 


	public String getZyh() {
		return zyh;
	}


	public void setZyh(String zyh) {
		this.zyh = zyh;
	}


	public String getBed() {
		return bed;
	}


	public void setBed(String bed) {
		this.bed = bed;
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


	public String getHy() {
		return hy;
	}


	public void setHy(String hy) {
		this.hy = hy;
	}


	public String getBq() {
		return bq;
	}


	public void setBq(String bq) {
		this.bq = bq;
	}


	public String getBgrq() {
		return bgrq;
	}


	public void setBgrq(String bgrq) {
		this.bgrq = bgrq;
	}


	public String getBlh() {
		return blh;
	}


	public void setBlh(String blh) {
		this.blh = blh;
	}


	public String getBglb() {
		return bglb;
	}


	public void setBglb(String bglb) {
		this.bglb = bglb;
	}


	public String getKdks() {
		return kdks;
	}


	public void setKdks(String kdks) {
		this.kdks = kdks;
	}


	public String getKdys() {
		return kdys;
	}


	public void setKdys(String kdys) {
		this.kdys = kdys;
	}


	public String getKdrq() {
		return kdrq;
	}


	public void setKdrq(String kdrq) {
		this.kdrq = kdrq;
	}


	public String getBbmc() {
		return bbmc;
	}


	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}


	public String getLczd() {
		return lczd;
	}


	public void setLczd(String lczd) {
		this.lczd = lczd;
	}


	public String getBlzd() {
		return blzd;
	}


	public void setBlzd(String blzd) {
		this.blzd = blzd;
	}


	public String getBgys() {
		return bgys;
	}


	public void setBgys(String bgys) {
		this.bgys = bgys;
	}


	public String getShys() {
		return shys;
	}


	public void setShys(String shys) {
		this.shys = shys;
	}


	public static JcBingLiInfo parse(JSONObject jsonObject) throws JSONException{
		JcBingLiInfo jcObj = new JcBingLiInfo();
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
			jcObj.setBed(obj.has("bed")?obj.getString("bed"):"");
			jcObj.setName(obj.has("name")?obj.getString("name"):"");
			jcObj.setSex(obj.has("sex")?obj.getString("sex"):"");
			jcObj.setNl(obj.has("nl")?obj.getString("nl"):"");
			jcObj.setHy(obj.has("hy")?obj.getString("hy"):"");
			jcObj.setBq(obj.has("bq")?obj.getString("bq"):"");
			jcObj.setBgrq(obj.has("bgrq")?obj.getString("bgrq"):"");
			jcObj.setBlh(obj.has("blh")?obj.getString("blh"):"");
			jcObj.setBglb(obj.has("bglb")?obj.getString("bglb"):"");
			jcObj.setKdks(obj.has("kdks")?obj.getString("kdks"):"");
			jcObj.setKdys(obj.has("kdys")?obj.getString("kdys"):"");
			jcObj.setKdrq(obj.has("kdrq")?obj.getString("kdrq"):"");
			jcObj.setBbmc(obj.has("bbmc")?obj.getString("bbmc"):"");
			jcObj.setLczd(obj.has("lczd")?obj.getString("lczd"):"");
			jcObj.setBlzd(obj.has("blzd")?obj.getString("blzd"):"");
			jcObj.setBgys(obj.has("bgys")?obj.getString("bgys"):"");
			jcObj.setShys(obj.has("shys")?obj.getString("shys"):"");

		}else{
			jcObj.setMessage(jsonObject.getString("message"));
		}
		return jcObj;
	}


}
