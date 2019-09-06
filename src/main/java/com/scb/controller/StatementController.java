package com.scb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/get")
public class StatementController {
	@Autowired
	RestTemplate restTemplate;
	@SuppressWarnings("rawtypes")

	@GetMapping("/getTransDtls")
	public ResponseEntity<?> getTransactionDetails(@RequestParam("acctNo") Integer acctNo){
		 String uri = "http://localhost:2020/cashflow/payment/transhistory?acctNo="+acctNo;
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		 map.add("acctNo", acctNo);
		 HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
		 Object[] response = restTemplate.getForObject(uri, Object[].class, request);
		 return new ResponseEntity<List>(Arrays.asList(response),HttpStatus.OK);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
