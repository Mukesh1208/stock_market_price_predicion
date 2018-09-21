package com.model;

import java.io.Serializable;

public class CompanyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
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

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	public String getYahooFinanceName() {
		return yahooFinanceName;
	}

	public void setYahooFinanceName(String yahooFinanceName) {
		this.yahooFinanceName = yahooFinanceName;
	}

	private int companyId;

	private String companyName; 

	private int companyType;
	
	private String yahooFinanceName;

	

}
