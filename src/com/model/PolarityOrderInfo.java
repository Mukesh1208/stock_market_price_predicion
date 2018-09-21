package com.model;

import java.io.Serializable;

public class PolarityOrderInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotalPositive() {
		return totalPositive;
	}

	public void setTotalPositive(int totalPositive) {
		this.totalPositive = totalPositive;
	}

	public int getTotalNegative() {
		return totalNegative;
	}

	public void setTotalNegative(int totalNegative) {
		this.totalNegative = totalNegative;
	}

	public int getTotalFeature() {
		return totalFeature;
	}

	public void setTotalFeature(int totalFeature) {
		this.totalFeature = totalFeature;
	}

	public int getTotalNeutral() {
		return totalNeutral;
	}

	public void setTotalNeutral(int totalNeutral) {
		this.totalNeutral = totalNeutral;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private int companyId;

	private int type;

	private int totalPositive;

	private int totalNegative;

	private int totalFeature;

	private int totalNeutral;

	private String companyName;

}
