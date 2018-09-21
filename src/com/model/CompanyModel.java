package com.model;

import java.io.Serializable;

public class CompanyModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int companyId; 

	public void setFeatureVector(double featureVector) {
		this.featureVector = featureVector;
	}

	public double getFeatureVector() {
		return featureVector;
	}

	public void setFeatureCorrelation(double featureCorrelation) {
		this.featureCorrelation = featureCorrelation;
	}

	public double getFeatureCorrelation() {
		return featureCorrelation;
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

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	private String companyName;

	private int companyType; 

	private double featureVector;

	private double featureCorrelation;

}
