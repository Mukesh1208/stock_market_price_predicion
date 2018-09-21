package com.model;

import java.io.Serializable;
import java.sql.Blob;

public class CleanReviewModel implements Serializable {

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

	public Blob getCleanReviewDetails() {
		return cleanReviewDetails;
	}

	public void setCleanReviewDetails(Blob cleanReviewDetails) {
		this.cleanReviewDetails = cleanReviewDetails;
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

	private int companyId;

	private int companyType;

	private Blob cleanReviewDetails;

}
