package com.model;

import java.io.Serializable;

public class HashTagInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hashtag;

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



	private String companyId; 

}
