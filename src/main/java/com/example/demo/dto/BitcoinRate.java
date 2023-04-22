package com.example.demo.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitcoinRate {
	
	
	private Time time;
	
	private String disclaimer;
	
	private String chartName;
    
    @JsonProperty("bpi")
    private Map<String, Currency> currencies;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Map<String, Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

}
