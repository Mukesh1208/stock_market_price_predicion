package com.model;

import java.io.Serializable;

public class RankCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int companyId;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getNegativePolarity() {
		return negativePolarity;
	}

	public void setNegativePolarity(int negativePolarity) {
		this.negativePolarity = negativePolarity;
	}

	public int getNeutralPolarity() {
		return neutralPolarity;
	}

	public void setNeutralPolarity(int neutralPolarity) {
		this.neutralPolarity = neutralPolarity;
	}

	public int getTotalFeature() {
		return totalFeature;
	}

	public void setTotalFeature(int totalFeature) {
		this.totalFeature = totalFeature;
	}

	public int getPositivePolarity() {
		return positivePolarity;
	}

	public void setPositivePolarity(int positivePolarity) {
		this.positivePolarity = positivePolarity;
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

	private String companyName;

	private String featureType;

	private int positivePolarity;

	private int negativePolarity;

	private int neutralPolarity;

	private int totalFeature;

}
