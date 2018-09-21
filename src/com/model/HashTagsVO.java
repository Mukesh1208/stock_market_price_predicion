package com.model;

import java.io.Serializable;

public class HashTagsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hashTagId;

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getHashTagId() {
		return hashTagId;
	}

	public void setHashTagId(int hashTagId) {
		this.hashTagId = hashTagId;
	}

	

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	private String hashtag;
 
	private int companyId;

}
