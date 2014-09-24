package com.tynet.app.bean;

import java.io.Serializable;

public class Jybg implements Serializable {

	private static final long serialVersionUID = 1L;
	private String patientid;// String 住院号
	private String patientname;// String 姓名
	private String departBed;// String 床号
	private String nl;// String 年龄
	private String zd;// String 诊断
	private String sqh;// String 条形码号
	private String sampletype;// String 标本类型
	private String examinaim;// String 检验项目
	private String requester;// String 送检医生
	private String executetime;// String 采样时间
	private String jyz;// String 检验者
	private String jyrq;// String 检验日期
	private String checkoperator;// String 复核员
	private String checktime;// String --报告日期 即复核时间
	private String ybh;// String --内部关联用

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getDepartBed() {
		return departBed;
	}

	public void setDepartBed(String departBed) {
		this.departBed = departBed;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getSqh() {
		return sqh;
	}

	public void setSqh(String sqh) {
		this.sqh = sqh;
	}

	public String getSampletype() {
		return sampletype;
	}

	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}

	public String getExaminaim() {
		return examinaim;
	}

	public void setExaminaim(String examinaim) {
		this.examinaim = examinaim;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getExecutetime() {
		return executetime;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}

	public String getJyz() {
		return jyz;
	}

	public void setJyz(String jyz) {
		this.jyz = jyz;
	}

	public String getJyrq() {
		return jyrq;
	}

	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}

	public String getCheckoperator() {
		return checkoperator;
	}

	public void setCheckoperator(String checkoperator) {
		this.checkoperator = checkoperator;
	}

	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public String getYbh() {
		return ybh;
	}

	public void setYbh(String ybh) {
		this.ybh = ybh;
	}

}
