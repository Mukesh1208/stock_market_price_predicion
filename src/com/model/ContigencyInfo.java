package com.model;

public class ContigencyInfo {
	
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

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getNegativeProbability() {
		return negativeProbability;
	}

	public void setNegativeProbability(double negativeProbability) {
		this.negativeProbability = negativeProbability;
	}

	public double getTotalPositiveOthers() {
		return totalPositiveOthers;
	}

	public void setTotalPositiveOthers(double totalPositiveOthers) {
		this.totalPositiveOthers = totalPositiveOthers;
	}

	public double getTotalNegativeOthers() {
		return totalNegativeOthers;
	}

	public void setTotalNegativeOthers(double totalNegativeOthers) {
		this.totalNegativeOthers = totalNegativeOthers;
	}

	private String catName;
	
	private double probability;
	
	private double negativeProbability;
	
	private double totalPositiveOthers;
	
	private double totalNegativeOthers;


}
