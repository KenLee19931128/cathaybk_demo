package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.DataDicRepository;
import com.example.demo.dto.BitcoinRate;
import com.example.demo.dto.BitcoinRateDto;
import com.example.demo.dto.CurrencyInfo;
import com.example.demo.entity.DataDic;

@Service
public class BitcoinExchangeRateService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DataDicRepository dataDicRepository;

	public static final String COINDESK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	public Object getCoindeskAPI() {
		return restTemplate.getForObject(COINDESK_URL, Object.class);
	}

	public BitcoinRateDto getBitcoinRate() {
		Map<String, String> currencyDic = dataDicRepository.findByType("CURRENCY").stream()
				.collect(Collectors.toMap(DataDic::getKey, DataDic::getValue));
		BitcoinRate result = restTemplate.getForObject(COINDESK_URL, BitcoinRate.class);
		BitcoinRateDto dto = new BitcoinRateDto();
		List<CurrencyInfo> currencies = new ArrayList<>();
		result.getCurrencies().forEach((k, v) -> {
			CurrencyInfo currencyInfo = new CurrencyInfo(v.getCode(), currencyDic.get(v.getCode()), v.getRate());
			currencies.add(currencyInfo);
		});

		dto.setCurrencyInfo(currencies);
		dto.setUpdatTime(dateFormat(result.getTime().getUpdatedISO()));

		return dto;

	}

	private String dateFormat(String dateStr) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, inputFormatter);
		String formattedDate = dateTime.format(outputFormatter);
		return formattedDate;
	}

}
