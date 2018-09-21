package com.model;

import java.io.Serializable;
import java.sql.Blob;

public class ReviewModelObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int reviewId;

	private int companyId;

	private String reviewDetails;

	private int companyType;

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}

	public String getReviewDetails() {
		return reviewDetails;
	}

	public void setReviewDetailsBlob(Blob reviewDetailsBlob) {
		this.reviewDetailsBlob = reviewDetailsBlob;
	}

	public Blob getReviewDetailsBlob() {
		return reviewDetailsBlob;
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

	private Blob reviewDetailsBlob;

}
