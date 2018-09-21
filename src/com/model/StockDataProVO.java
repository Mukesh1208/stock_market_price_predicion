package com.model;

import java.io.Serializable;

public class StockDataProVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getMarketIndex() {
		return marketIndex;
	}

	public void setMarketIndex(double marketIndex) {
		this.marketIndex = marketIndex;
	}

	public double getDailyTurnOver() {
		return dailyTurnOver;
	}

	public void setDailyTurnOver(double dailyTurnOver) {
		this.dailyTurnOver = dailyTurnOver;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public boolean isSucess() {
		return isSucess;
	}

	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private double volume;

	private double marketIndex;

	private double dailyTurnOver;

	private int companyId;
	
	private boolean isSucess;
	
	
	private String error;
	

}
