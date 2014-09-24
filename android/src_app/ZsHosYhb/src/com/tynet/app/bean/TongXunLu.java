package com.tynet.app.bean;

import java.io.Serializable;

public class TongXunLu implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contactId;// String 成员ID
	private String userName;// String 姓名
	private String userJob;// String 职务
	private String mobile;// String 手机号码
	private String shortMobile;// String 手机短号
	private String phone;// String 座机号码（外线）
	private String shortPhone;// String 内线号码（内线）
	private String smallMobil;// String 小灵通
	private String homePhone;// String 宅电
	private String groupType;// String 分组（0科室。1个人）
	private String platHosId;// String 医院编号
	private String platDocId;// String 关联平台医生编号
	private String remarks;// String 备注
	private String qq;// String qq
	private String email;// String 邮箱
	private String deptName;// String 科室名称
	private String showNo;// String 排序（升序排）
	//====================额外加的字段
	private String sortLetters;  //显示数据拼音的首字母
	
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShortMobile() {
		return shortMobile;
	}
	public void setShortMobile(String shortMobile) {
		this.shortMobile = shortMobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShortPhone() {
		return shortPhone;
	}
	public void setShortPhone(String shortPhone) {
		this.shortPhone = shortPhone;
	}
	public String getSmallMobil() {
		return smallMobil;
	}
	public void setSmallMobil(String smallMobil) {
		this.smallMobil = smallMobil;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getPlatHosId() {
		return platHosId;
	}
	public void setPlatHosId(String platHosId) {
		this.platHosId = platHosId;
	}
	public String getPlatDocId() {
		return platDocId;
	}
	public void setPlatDocId(String platDocId) {
		this.platDocId = platDocId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getShowNo() {
		return showNo;
	}
	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}


}
