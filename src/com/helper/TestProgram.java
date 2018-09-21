package com.helper;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class TestProgram {

	public static void main(String[] args) throws IOException {
		
		Stock stock = YahooFinance.get("GGQ1.DE");
		 
		BigDecimal price = stock.getQuote().getPrice();
		
		System.out.println("Price ="+price);
			
		
		BigDecimal change = stock.getQuote().getChangeInPercent();
		System.out.println("Change ="+change);
		
		BigDecimal peg = stock.getStats().getPeg();
		
		System.out.println("Peg ="+peg); 
		
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		
		System.out.println("Divident"+ dividend);
		
		Long volume =stock.getQuote().getVolume();
		
		System.out.println("Volume"+volume);
		
		BigDecimal value =stock.getQuote().getPreviousClose();
		
		System.out.println("Daily Turnover" +value);
		
		
		
		System.out.println("======================================================================");
		
		
		
		 
		stock.print();

	}

}
