package com.model;

import java.io.Serializable;
import java.util.List;

public class CompanyRankInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CompanyRankInfo> collegeRankInfoList;

	private List<RankCompany> rankCollegeList;

	public List<CompanyRankInfo> getCollegeRankInfoList() {
		return collegeRankInfoList;
	}

	public void setCollegeRankInfoList(List<CompanyRankInfo> collegeRankInfoList) {
		this.collegeRankInfoList = collegeRankInfoList;
	}

	public List<RankCompany> getRankCollegeList() {
		return rankCollegeList;
	}

	public void setRankCollegeList(List<RankCompany> rankCollegeList) {
		this.rankCollegeList = rankCollegeList;
	}

}
