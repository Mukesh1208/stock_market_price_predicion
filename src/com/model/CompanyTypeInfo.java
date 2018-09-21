package com.model;

import java.io.Serializable;

public class CompanyTypeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int companyTypeId;

	private String companyName;

	public int getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
