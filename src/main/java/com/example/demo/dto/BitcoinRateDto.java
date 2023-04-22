package com.example.demo.dto;

import java.util.List;

public class BitcoinRateDto {
	
	private String updatTime;
	
	private List<CurrencyInfo> currencyInfo;

	public String getUpdatTime() {
		return updatTime;
	}

	public void setUpdatTime(String updatTime) {
		this.updatTime = updatTime;
	}

	public List<CurrencyInfo> getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(List<CurrencyInfo> currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
	

}
