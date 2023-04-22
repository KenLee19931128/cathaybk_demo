package com.example.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.BitcoinRateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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
	public void testGetCoindeskAPI() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/getCoindeskAPI", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		String jsonBody = response.getBody();
		System.out.println(jsonBody);
		assertNotNull(jsonBody);

		ObjectMapper objectMapper = new ObjectMapper();
		assertTrue(objectMapper.readTree(jsonBody).has("time"));
		assertTrue(objectMapper.readTree(jsonBody).has("bpi"));
	}

	@Test
	public void testGetBitcoinRate() throws Exception {
		ResponseEntity<BitcoinRateDto> response = restTemplate.getForEntity(baseUrl + "/getBitcoinRate", BitcoinRateDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		BitcoinRateDto result = response.getBody();
		Gson gson = new Gson();
		System.out.println(gson.toJson(result));
		assertNotNull(result);
		assertNotNull(result.getCurrencyInfo());
		assertTrue(result.getCurrencyInfo().size() > 0);
		assertNotNull(result.getUpdatTime());
	}

}
