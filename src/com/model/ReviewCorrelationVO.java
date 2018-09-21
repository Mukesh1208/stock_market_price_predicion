package com.model;

import java.io.Serializable;

public class ReviewCorrelationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public int getCollegeType() {
		return collegeType;
	}

	public void setCollegeType(int collegeType) {
		this.collegeType = collegeType;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	private int reviewId;

	private int count;

	private String word;

	private int collegeId;

	private int collegeType;

	private String collegeName;

}
