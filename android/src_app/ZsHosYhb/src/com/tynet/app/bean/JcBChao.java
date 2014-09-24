package com.tynet.app.bean;

import java.io.Serializable;

public class JcBChao implements Serializable {

	private static final long serialVersionUID = 1L;
	private String zyh;// String 住院号
	private String name;// String 姓名
	private String sex;// String 性别
	private String nl;// String 年龄
	private String sqks;// String 申请科室
	private String bgsj;// String 报告时间
	private String bbys;// String 报告医生
	private String jcxm;// String -检查项目
	private String lsh;// String 流水号
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

}
