package com.model;

import java.io.Serializable;

public class FrequencyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int freqId;

	public int getFreqId() {
		return freqId;
	}

	public void setFreqId(int freqId) {
		this.freqId = freqId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getFreq() {
		return freq;
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

	private String tokenName;

	private int freq;

	private int companyId;

	private int companyType;

}
