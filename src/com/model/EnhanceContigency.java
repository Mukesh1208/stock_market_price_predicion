package com.model;

public class EnhanceContigency {
	
	private int tweetId;
	
	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public double getPositiveCatRatio() {
		return positiveCatRatio;
	}

	public void setPositiveCatRatio(double positiveCatRatio) {
		this.positiveCatRatio = positiveCatRatio;
	}

	public double getOtherCatRatio() {
		return otherCatRatio;
	}

	public void setOtherCatRatio(double otherCatRatio) {
		this.otherCatRatio = otherCatRatio;
	}

	private String catName;
	
	private double positiveCatRatio;
	
	private double otherCatRatio;

}
