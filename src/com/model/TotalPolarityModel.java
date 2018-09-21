package com.model;

import java.io.Serializable;

public class TotalPolarityModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getPositiveRating() {
		return positiveRating;
	}

	public void setPositiveRating(int positiveRating) {
		this.positiveRating = positiveRating;
	}

	public int getNegativeRating() {
		return negativeRating;
	}

	public void setNegativeRating(int negativeRating) {
		this.negativeRating = negativeRating;
	}

	public int getNeutralRating() {
		return neutralRating;
	}

	public void setNeutralRating(int neutralRating) {
		this.neutralRating = neutralRating;
	}

	private int positiveRating;

	private int negativeRating;

	private int neutralRating;

	private int companyId;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getTotalFeature() {
		return totalFeature;
	}

	public void setTotalFeature(int totalFeature) {
		this.totalFeature = totalFeature;
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

	private int companyType;

	private String featureType;

	private int totalFeature;

}
