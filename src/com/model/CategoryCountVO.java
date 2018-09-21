package com.model;

public class CategoryCountVO {
	
	private int noOfTweets;
	
	public int getNoOfTweets() {
		return noOfTweets;
	}

	public void setNoOfTweets(int noOfTweets) {
		this.noOfTweets = noOfTweets;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	private String catName;

}
