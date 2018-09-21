package com.model;

public class ActivationProbabilityVO {

	private String groupType;

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public double getActivationProbability() {
		return activationProbability;
	}

	public void setActivationProbability(double activationProbability) {
		this.activationProbability = activationProbability;
	}

	public String getProductToPush() {
		return productToPush;
	}

	public void setProductToPush(String productToPush) {
		this.productToPush = productToPush;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private double activationProbability;

	private String productToPush;

	private int count;

	private String userId;

}
