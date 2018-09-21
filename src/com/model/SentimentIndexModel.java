package com.model;

import java.io.Serializable;

public class SentimentIndexModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int negativeRating;

	public int getNegativeRating() {
		return negativeRating;
	}

	public void setNegativeRating(int negativeRating) {
		this.negativeRating = negativeRating;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getPositiveRating() {
		return positiveRating;
	}

	public void setPositiveRating(int positiveRating) {
		this.positiveRating = positiveRating;
	}

	public double getSentimentIndex() {
		return sentimentIndex;
	}

	public void setSentimentIndex(double sentimentIndex) {
		this.sentimentIndex = sentimentIndex;
	}

	public double getSentimentDescIndex() {
		return sentimentDescIndex;
	}

	public void setSentimentDescIndex(double sentimentDescIndex) {
		this.sentimentDescIndex = sentimentDescIndex;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getSentimentIndexId() {
		return sentimentIndexId;
	}

	public void setSentimentIndexId(int sentimentIndexId) {
		this.sentimentIndexId = sentimentIndexId;
	}

	private int companyId;

	private int positiveRating;

	private double sentimentIndex;

	private double sentimentDescIndex;
	
	private String company;
	
	private int sentimentIndexId;

}
