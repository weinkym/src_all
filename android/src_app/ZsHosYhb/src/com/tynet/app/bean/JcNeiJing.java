package com.tynet.app.bean;

import java.io.Serializable;

public class JcNeiJing implements Serializable {

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


}
