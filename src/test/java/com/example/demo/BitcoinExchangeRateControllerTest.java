package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.BitcoinRateDto;
import com.example.demo.entity.DataDic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BitcoinExchangeRateControllerTest {

	@org.springframework.boot.test.web.server.LocalServerPort
	private int port;

	private String baseUrl;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		baseUrl = "http://localhost:" + port + "/api";
	}

	@Test
	@Order(1)
	public void testGetCoindeskAPI() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/getCoindeskAPI", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		String jsonBody = response.getBody();
		System.out.println("----------TestGetCoindeskAPI----------");
		System.out.println(jsonBody);
		assertNotNull(jsonBody);

		ObjectMapper objectMapper = new ObjectMapper();
		assertTrue(objectMapper.readTree(jsonBody).has("time"));
		assertTrue(objectMapper.readTree(jsonBody).has("bpi"));
	}

	@Test
	@Order(2)
	public void testGetBitcoinRate() throws Exception {
		ResponseEntity<BitcoinRateDto> response = restTemplate.getForEntity(baseUrl + "/getBitcoinRate", BitcoinRateDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		BitcoinRateDto result = response.getBody();
		Gson gson = new Gson();
		System.out.println("----------testGetBitcoinRate API----------");
		System.out.println(gson.toJson(result));
		assertNotNull(result);
		assertNotNull(result.getCurrencyInfo());
		assertTrue(result.getCurrencyInfo().size() > 0);
		assertNotNull(result.getUpdatTime());
	}
	
	@Test
	@Order(3)
	public void testGetCurrencyDic() throws Exception {
		ResponseEntity<List<DataDic>> response = restTemplate.exchange(baseUrl + "/getCurrencyDic", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataDic>>() {
		});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		List<DataDic> result = response.getBody();
		assertNotNull(result);
	}
	
	@Test
	@Order(4)
	public void testAddCurrencyDic() throws Exception {
		DataDic dataDic = new DataDic();
		dataDic.setType("CURRENCY");
		dataDic.setKey("TWD");
		dataDic.setValue("台幣");
		ResponseEntity<DataDic> response = restTemplate.postForEntity(baseUrl + "/addCurrencyDic", dataDic, DataDic.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		DataDic result = response.getBody();
		assertNotNull(result);
	}
	
	@Test
	@Order(5)
	public void testUpdateCurrencyDic() throws Exception {
		DataDic dataDic = new DataDic();
		dataDic.setType("CURRENCY");
		dataDic.setKey("USD");
		dataDic.setValue("美元2");
		restTemplate.put(baseUrl + "/updateCurrencyDic", dataDic, DataDic.class);
		ResponseEntity<List<DataDic>> response = restTemplate.exchange(baseUrl + "/getCurrencyDic", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataDic>>() {
		});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		List<DataDic> result = response.getBody();
		Gson gson = new Gson();
		System.out.println("----------testUpdateCurrencyDic API----------");
		System.out.println(gson.toJson(result));
		assertNotNull(result);
	}
	
	@Test
	@Order(6)
	public void testDeleteCurrencyDic() throws Exception {
		restTemplate.delete(baseUrl + "/deleteCurrencyDic/CURRENCY/EUR");
		ResponseEntity<List<DataDic>> response = restTemplate.exchange(baseUrl + "/getCurrencyDic", HttpMethod.GET, null, new ParameterizedTypeReference<List<DataDic>>() {
		});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		List<DataDic> result = response.getBody();
		assertNotNull(result);
	}

}
