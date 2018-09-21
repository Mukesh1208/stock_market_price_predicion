package com.teststock;

import java.util.List;
import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

public class TestObj {

	public static void main(String s[]) {

		String apiKey = "50M3AP1K3Y";
		int timeout = 3000;
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
		TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

		try {
			// IntraDay response = stockTimeSeries.intraDay("MSFT",
			// Interval.ONE_MIN, OutputSize.COMPACT);

			IntraDay response = stockTimeSeries.intraDay("WMT", Interval.ONE_MIN, OutputSize.COMPACT);
			Map<String, String> metaData = response.getMetaData();
			System.out.println("Information: " + metaData.get("1. Information"));
			System.out.println("Stock: " + metaData.get("2. Symbol"));

			double open = 0;

			double high = 0;

			double low = 0;

			double close = 0;

			double volume = 0;

			List<StockData> stockData = response.getStockData();

			int noOfRecords = 0;

			for (StockData stock : stockData) {
				noOfRecords = noOfRecords + 1;
				open = open + stock.getOpen();
				high = high + stock.getHigh();
				low = low + stock.getLow();
				close = close + stock.getClose();
				volume = volume + stock.getVolume();
			}
			
			if(open>0){
				open =((double) open)/((double) noOfRecords);
			}
			
			if(high>0){
				high =((double) high)/((double) noOfRecords);
			}
			
			if(low>0){
				low =((double) low)/((double) noOfRecords);
			}
			
			if(close>0){
				close =((double) close)/((double) noOfRecords);
			}
			
			if(volume>0){
				volume =((double) volume)/((double) noOfRecords);
			}
			
			

		} catch (AlphaVantageException e) {
			System.out.println("something went wrong");
		}
	}
}
