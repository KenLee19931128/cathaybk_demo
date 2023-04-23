package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DataDic;
import com.example.demo.service.BitcoinExchangeRateService;

@RestController
public class BitcoinExchangeRateController {

	@Autowired
	private BitcoinExchangeRateService service;

	/**
	 * 取得 CoindeskAPI
	 */
	@GetMapping(value = "/api/getCoindeskAPI", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getCoindeskAPI() {
		return service.getCoindeskAPI();
	}

	/**
	 * 資料轉換API
	 */
	@GetMapping(value = "/api/getBitcoinRate", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getBitcoinRate() {
		return service.getBitcoinRate();
	}
	
	/**
	 * 查詢幣別對應表資料 API
	 */
	@GetMapping(value = "/api/getCurrencyDic", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DataDic> getCurrencyDic() {
		return service.findCurrencyDic();
	}
	
	/**
	 * 叫新增幣別對應表資料 API
	 */
	@PostMapping(value = "/api/addCurrencyDic",produces = MediaType.APPLICATION_JSON_VALUE)
	public DataDic addCurrencyDic(@RequestBody DataDic dic) {
		return service.saveOrUpdateCurrencyDic(dic);
	}
	
	/**
	 * 更新幣別對應表資料 API
	 */
	@PutMapping(value = "/api/updateCurrencyDic", produces = MediaType.APPLICATION_JSON_VALUE)
	public DataDic updateCurrencyDic(@RequestBody DataDic dic) {
		return service.saveOrUpdateCurrencyDic(dic);
	}
	
	/**
	 * 刪除幣別對應表資料 API
	 */
	@DeleteMapping(value = "/api/deleteCurrencyDic/{type}/{key}")
	public void getCurrencyDic(@PathVariable("type") String type, @PathVariable("key") String key) {
		service.deleteCurrencyDic(type, key);
	}

}
