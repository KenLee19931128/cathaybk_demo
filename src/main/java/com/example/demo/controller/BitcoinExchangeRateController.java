package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BitcoinExchangeRateService;

@RestController
public class BitcoinExchangeRateController {

	@Autowired
	private BitcoinExchangeRateService service;

	/**
	 * 取得 CoindeskAPI
	 *
	 * @param name
	 */
	@GetMapping(value = "/api/getCoindeskAPI", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getCoindeskAPI() {
		return service.getCoindeskAPI();
	}

	/**
	 * 取得 BitcoinRate
	 *
	 * @param name
	 */
	@GetMapping(value = "/api/getBitcoinRate", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getBitcoinRate() {
		return service.getBitcoinRate();
	}

}
