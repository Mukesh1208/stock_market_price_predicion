package com.model;

import java.io.Serializable;

public class TokenVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int tokenId;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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


	private String tokenName;

	private int reviewId;

	private int companyId; 

	private int companyType; 

}
