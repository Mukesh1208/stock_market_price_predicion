package com.model;

public class ReviewDetailModel extends ReviewModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String companyName; 
 
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private int companyType;

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	

}
