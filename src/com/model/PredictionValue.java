package com.model;



public class PredictionValue extends StockDataProVO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public double getSentimentDescIndex() {
		return sentimentDescIndex;
	}

	public void setSentimentDescIndex(double sentimentDescIndex) {
		this.sentimentDescIndex = sentimentDescIndex;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getPredictionValue() {
		return predictionValue;
	}

	public void setPredictionValue(double predictionValue) {
		this.predictionValue = predictionValue;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	private double sentimentDescIndex;

	private double beta;

	private double predictionValue;
	
	private String company;

}
