package com.model;

public class FeatureVO {

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	private int featureId;

	private int reviewId;

	private String featureType;

	private int featureBasedFreq;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getFeatureBasedFreq() {
		return featureBasedFreq;
	}

	public void setFeatureBasedFreq(int featureBasedFreq) {
		this.featureBasedFreq = featureBasedFreq;
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

	private int companyId;

	private int companyType;

}
