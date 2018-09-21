package com.model;

import java.io.Serializable;

public class CategoryInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int catId;
	
	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatWord() {
		return catWord;
	}

	public void setCatWord(String catWord) {
		this.catWord = catWord;
	}

	private String catName;
	
	private String catWord;

}
