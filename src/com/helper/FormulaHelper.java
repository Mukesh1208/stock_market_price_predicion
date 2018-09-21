package com.helper;

import com.model.PredictionValue;

public class FormulaHelper {

	public double computeSentimentIndex(int noOfPositive, int noOfNegative) {

		double numerator = 1 + noOfPositive;

		double denominator = 1 + noOfNegative;

		double ratio = (double) (numerator / denominator);

		double value = Math.log(ratio);

		return value;

	}

	public double computeSentimentDiscIndex(int noOfPositive, int noOfNegative) {

		double numerator = noOfPositive - noOfNegative;

		double denominator = noOfPositive + noOfNegative;

		double ratio = (double) (numerator / denominator);

		double value = 1 - ratio;

		return value;

	}

	public double computePrediction(PredictionValue predictionValueObj) {

		double predictionValue = (0.6238 * predictionValueObj.getPrice())
				+ (0.0455 * predictionValueObj.getVolume())
				+ (0.0213 * predictionValueObj.getDailyTurnOver())
				+ (0.0316 * predictionValueObj.getMarketIndex())
				+ (0.0423 * predictionValueObj.getSentimentDescIndex() + predictionValueObj
						.getBeta());

		return predictionValue;

	}
	
	
	public static void main(String s[])
	{
		System.out.println("Value ="+Math.log(2.0417));
	}
}
