package com.tynet.app.bean;

import java.io.Serializable;

public class JcXinDian implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jcrq;// String 检查日期
	private String name;// String 姓名
	private String zyh;// String 住院号
	private String sex;// String 性别
	private String age;// String 年龄
	private String bed;// String 床号
	private String bq;// String 病区
	private String jcxm;// String 检查项目
	private String kdks;// String 开单科室
	private String guid;// String 唯一标示
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
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getBq() {
		return bq;
	}
	public void setBq(String bq) {
		this.bq = bq;
	}
	public String getJcxm() {
		return jcxm;
	}
	public void setJcxm(String jcxm) {
		this.jcxm = jcxm;
	}
	public String getKdks() {
		return kdks;
	}
	public void setKdks(String kdks) {
		this.kdks = kdks;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

}
