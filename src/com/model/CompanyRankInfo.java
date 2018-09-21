package com.model;

import java.io.Serializable;
import java.util.List;

public class CompanyRankInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ReviewCorrelationVO> reviewCollecctionVOList;

	public void setReviewCollecctionVOList(
			List<ReviewCorrelationVO> reviewCollecctionVOList) {
		this.reviewCollecctionVOList = reviewCollecctionVOList;
	}

	public List<ReviewCorrelationVO> getReviewCollecctionVOList() {
		return reviewCollecctionVOList;
	}

	public boolean isBasedOnFeature() {
		return isBasedOnFeature;
	}

	public void setBasedOnFeature(boolean isBasedOnFeature) {
		this.isBasedOnFeature = isBasedOnFeature;
	}

	public List<PolarityOrderInfo> getPolarityOrderInfos() {
		return polarityOrderInfos;
	}

	public void setPolarityOrderInfos(List<PolarityOrderInfo> polarityOrderInfos) {
		this.polarityOrderInfos = polarityOrderInfos;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public List<CompanyModel> getCollegeModel() {
		return collegeModel;
	}

	public void setCollegeModel(List<CompanyModel> collegeModel) {
		this.collegeModel = collegeModel;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	private List<CompanyModel> collegeModel;

	private boolean isBasedOnFeature;

	private List<PolarityOrderInfo> polarityOrderInfos;

	private String feature;

	private int collegeId;

	private String collegeName;

	private String featureType;

}
