package com.tynet.app.bean;

import java.io.Serializable;

public class Bq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bqdm;// String 病区代码

	private String bqmc;// String 病区名称

	private String srm1;// String 拼音码

	private String srm3;// String

	public String getBqdm() {
		return bqdm;
	}

	public String getBqmc() {
		return bqmc;
	}

	public String getSrm1() {
		return srm1;
	}

	public String getSrm3() {
		return srm3;
	}

	public void setBqdm(String bqdm) {
		this.bqdm = bqdm;
	}

	public void setBqmc(String bqmc) {
		this.bqmc = bqmc;
	}
	public void setSrm1(String srm1) {
		this.srm1 = srm1;
	}
	public void setSrm3(String srm3) {
		this.srm3 = srm3;
	}

}
