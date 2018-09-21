package com.model;

import java.io.Serializable;

public class ReviewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int companyId;

	public String getReviewDetails() {
		return reviewDetails;
	}

	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrlType(String webUrlType) {
		this.webUrlType = webUrlType;
	}

	public String getWebUrlType() {
		return webUrlType;
	}

	private String reviewDetails;

	private String webUrl;

	private String webUrlType;

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getTweetScreenName() {
		return tweetScreenName;
	}

	public void setTweetScreenName(String tweetScreenName) {
		this.tweetScreenName = tweetScreenName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
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

	private String hashTag;

	private String tweetScreenName;

	private String language;

	private String userId;

	private int tweetId;

	private String companyName;
	
	private int companyType;

}
