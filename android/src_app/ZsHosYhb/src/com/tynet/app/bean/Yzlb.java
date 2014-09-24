package com.tynet.app.bean;

import java.io.Serializable;

public class Yzlb implements Serializable {

	private static final long serialVersionUID = 1L;

	private String frequCode;// String 频率

	private String groupNo;// String 组号

	private String orderNo;// String 医嘱唯一标示

	private String physicianName;// String 开单医生
	
	private String startTime;// String 开始时间
	 
	private String supplyName;// String 用法
	
	private String yznr;// String 医嘱内容
	
	private String performanceTime;// String 执行时间
	
	private String stopTime;// String 停止时间    
	
	private String parentName;// String 父医嘱名

	public String getFrequCode() {
		return frequCode;
	}

	public void setFrequCode(String frequCode) {
		this.frequCode = frequCode;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getYznr() {
		return yznr;
	}

	public void setYznr(String yznr) {
		this.yznr = yznr;
	}

	public String getPerformanceTime() {
		return performanceTime;
	}

	public void setPerformanceTime(String performanceTime) {
		this.performanceTime = performanceTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}



}
