package com.model;

import java.io.Serializable;

public class CleanReviewUIModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cleanId;

	public int getCleanId() {
		return cleanId;
	}

	public void setCleanId(int cleanId) {
		this.cleanId = cleanId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public void setCleanReviewDetails(String cleanReviewDetails) {
		this.cleanReviewDetails = cleanReviewDetails;
	}

	public String getCleanReviewDetails() {
		return cleanReviewDetails;
	}

	

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}



	private int reviewId;
	private String cleanReviewDetails;
 
	private int companyId;

	private int companyType; 

}
