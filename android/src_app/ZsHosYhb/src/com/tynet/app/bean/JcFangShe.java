package com.tynet.app.bean;

import java.io.Serializable;

public class JcFangShe implements Serializable {

	private static final long serialVersionUID = 1L;
	private String approvedate;// String 报告日期
	private String examItemName;// String -检查类别
	private String examname;// String -检查项目
	private String clinicId;// String 住院号
	private String name;// String 姓名
	private String dept_name;// String 申请科室
	private String checkNumber;// String 放射科内部唯一判别   流水号
	private String gender;// String 性别
	private String age;// String 年龄

	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
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
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	
}
