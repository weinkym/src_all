package com.tynet.app.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

public class LsYzxq extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private String patientNo;// String 病人唯一标示

	private String bah;// String 病案号，即住院号

	private String name;// String 姓名

	private String orderNo;// String 医嘱唯一标示
	
	private String startTime;// String 开始时间
	 
	private String yznr;// String 医嘱内容
	
	private String supplyName;// String 用法
	
	private String frequCode;// String 频率
	
	private String physicianName;// String 开单医生    
	
	private String confirmTime;// String 核对时间
  
	private String yzlx;// String 医嘱类型  
	
	private String statusFlag;// String 医嘱状态 
	
	private String instruction;// String 医嘱描述
	
	private String dcjl;// String 单次剂量及单位
	
	private String dyl;// String 带药量及单位
	
	private String endTime;// String 停止时间(医生开的时候维护的停止时间)
	
	private String enterTime;// String 录入时间
	
	private String tzys;// String 停止医生
	
	private String statusTime;// String 停止执行时间
	
	private String tzhdr;// String 停止执行人
	
	private String physicianInstruction;// String 嘱托
	
	private String parentName;// String 父医嘱名
	
	private String psjg;// String 皮试结果
	
	private String groupNo;// String 组号
	
	private String zxTime;// String 执行时间
	
	private String zxr;// String 执行人
	
	private String hdr;// String 核对人
	
	private String orderName;// String 医嘱名称

	private String ypgg;// String 药品规格

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	public String getBah() {
		return bah;
	}

	public void setBah(String bah) {
		this.bah = bah;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getYznr() {
		return yznr;
	}

	public void setYznr(String yznr) {
		this.yznr = yznr;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getFrequCode() {
		return frequCode;
	}

	public void setFrequCode(String frequCode) {
		this.frequCode = frequCode;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getYzlx() {
		return yzlx;
	}

	public void setYzlx(String yzlx) {
		this.yzlx = yzlx;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getDcjl() {
		return dcjl;
	}

	public void setDcjl(String dcjl) {
		this.dcjl = dcjl;
	}

	public String getDyl() {
		return dyl;
	}

	public void setDyl(String dyl) {
		this.dyl = dyl;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getTzys() {
		return tzys;
	}

	public void setTzys(String tzys) {
		this.tzys = tzys;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public String getTzhdr() {
		return tzhdr;
	}

	public void setTzhdr(String tzhdr) {
		this.tzhdr = tzhdr;
	}

	public String getPhysicianInstruction() {
		return physicianInstruction;
	}

	public void setPhysicianInstruction(String physicianInstruction) {
		this.physicianInstruction = physicianInstruction;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPsjg() {
		return psjg;
	}

	public void setPsjg(String psjg) {
		this.psjg = psjg;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getZxTime() {
		return zxTime;
	}

	public void setZxTime(String zxTime) {
		this.zxTime = zxTime;
	}

	public String getZxr() {
		return zxr;
	}

	public void setZxr(String zxr) {
		this.zxr = zxr;
	}

	public String getHdr() {
		return hdr;
	}

	public void setHdr(String hdr) {
		this.hdr = hdr;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getYpgg() {
		return ypgg;
	}

	public void setYpgg(String ypgg) {
		this.ypgg = ypgg;
	}
	
	public static LsYzxq parse(JSONObject jsonObject) throws JSONException{
		LsYzxq yzxq = new LsYzxq();
		String code = jsonObject.getString("code");
		if(code.equals("0")){
			yzxq.setSuccess(true);
			JSONObject jsonYzxq = jsonObject.getJSONObject("info");
			yzxq.setPatientNo(jsonYzxq.has("patientNo")?jsonYzxq.getString("patientNo"):"");
			yzxq.setBah(jsonYzxq.has("bah")?jsonYzxq.getString("bah"):"");
			yzxq.setName(jsonYzxq.has("name")?jsonYzxq.getString("name"):"");
			yzxq.setOrderNo(jsonYzxq.has("orderNo")?jsonYzxq.getString("orderNo"):"");
			yzxq.setStartTime(jsonYzxq.has("startTime")?jsonYzxq.getString("startTime"):"");
			yzxq.setYznr(jsonYzxq.has("yznr")?jsonYzxq.getString("yznr"):"");
			yzxq.setSupplyName(jsonYzxq.has("supplyName")?jsonYzxq.getString("supplyName"):"");
			yzxq.setFrequCode(jsonYzxq.has("frequCode")?jsonYzxq.getString("frequCode"):"");
			yzxq.setPhysicianName(jsonYzxq.has("physicianName")?jsonYzxq.getString("physicianName"):"");
			yzxq.setConfirmTime(jsonYzxq.has("confirmTime")?jsonYzxq.getString("confirmTime"):"");
			yzxq.setYzlx(jsonYzxq.has("yzlx")?jsonYzxq.getString("yzlx"):"");
			yzxq.setStatusFlag(jsonYzxq.has("statusFlag")?jsonYzxq.getString("statusFlag"):"");
			yzxq.setInstruction(jsonYzxq.has("instruction")?jsonYzxq.getString("instruction"):"");
			yzxq.setDcjl(jsonYzxq.has("dcjl")?jsonYzxq.getString("dcjl"):"");
			yzxq.setDyl(jsonYzxq.has("dyl")?jsonYzxq.getString("dyl"):"");
			yzxq.setEndTime(jsonYzxq.has("endTime")?jsonYzxq.getString("endTime"):"");
			yzxq.setEnterTime(jsonYzxq.has("enterTime")?jsonYzxq.getString("enterTime"):"");
			yzxq.setTzys(jsonYzxq.has("tzys")?jsonYzxq.getString("tzys"):"");
			yzxq.setStatusTime(jsonYzxq.has("statusTime")?jsonYzxq.getString("statusTime"):"");
			yzxq.setTzhdr(jsonYzxq.has("tzhdr")?jsonYzxq.getString("tzhdr"):"");
			yzxq.setPhysicianInstruction(jsonYzxq.has("physicianInstruction")?jsonYzxq.getString("physicianInstruction"):"");
			yzxq.setParentName(jsonYzxq.has("parentName")?jsonYzxq.getString("parentName"):"");
			yzxq.setPsjg(jsonYzxq.has("psjg")?jsonYzxq.getString("psjg"):"");
			yzxq.setGroupNo(jsonYzxq.has("groupNo")?jsonYzxq.getString("groupNo"):"");
			yzxq.setZxTime(jsonYzxq.has("zxTime")?jsonYzxq.getString("zxTime"):"");
			yzxq.setZxr(jsonYzxq.has("zxr")?jsonYzxq.getString("zxr"):"");
			yzxq.setHdr(jsonYzxq.has("hdr")?jsonYzxq.getString("hdr"):"");
			yzxq.setOrderName(jsonYzxq.has("orderName")?jsonYzxq.getString("orderName"):"");
			yzxq.setYpgg(jsonYzxq.has("ypgg")?jsonYzxq.getString("ypgg"):"");

	

		}else{
			yzxq.setMessage(jsonObject.getString("message"));
		}
		return yzxq;
	}
	
	
	
}
