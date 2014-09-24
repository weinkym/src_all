package com.tynet.app.bean;

import java.io.Serializable;

public class JybgInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String englishab;// String 项目代号
	private String chinesename;// String 项目名称
	private String testresult;// String 检验结果
	private String range;// String 参考值
	private String unit;// String 单位
	private String resultflag;// String 偏高或偏低H偏高L偏低M正常
	private String testid;// String 项目序号
	public String getEnglishab() {
		return englishab;
	}
	public void setEnglishab(String englishab) {
		this.englishab = englishab;
	}
	public String getChinesename() {
		return chinesename;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
	public String getTestresult() {
		return testresult;
	}
	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getResultflag() {
		return resultflag;
	}
	public void setResultflag(String resultflag) {
		this.resultflag = resultflag;
	}
	public String getTestid() {
		return testid;
	}
	public void setTestid(String testid) {
		this.testid = testid;
	}

}
