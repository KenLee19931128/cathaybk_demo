package com.example.demo.dto;

public class CurrencyInfo {
	
	private String currency;
	
	private String currencyName;
	
	private String rate;
	
	public CurrencyInfo(String currency, String currencyName, String rate) {
		super();
		this.currency = currency;
		this.currencyName = currencyName;
		this.rate = rate;
	}
	
	public CurrencyInfo() {
		super();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
