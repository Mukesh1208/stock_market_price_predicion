package com.model;

public class PartialClassifierInfo {

	private String catName;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public double getPositiveRatio() {
		return positiveRatio;
	}

	public void setPositiveRatio(double positiveRatio) {
		this.positiveRatio = positiveRatio;
	}

	public double getNegativeRatio() {
		return negativeRatio;
	}

	public void setNegativeRatio(double negativeRatio) {
		this.negativeRatio = negativeRatio;
	}

	private int tweetId;

	private double positiveRatio;

	private double negativeRatio;

}
