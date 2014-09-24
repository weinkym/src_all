package com.tynet.app.bean;

import java.io.Serializable;

public class JcBingLi implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bglb;// String 报告类别
	private String name;// String 姓名
	private String zyh;// String 住院号
	private String bq;// String 病区
	private String bgrq;// String 报告日期
	private String blh;// String 病理号
	private String sex;// String 性别
	private String nl;// String 年龄
	public String getBglb() {
		return bglb;
	}
	public void setBglb(String bglb) {
		this.bglb = bglb;
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

}
