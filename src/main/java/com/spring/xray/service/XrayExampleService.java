package com.spring.xray.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.xray.spring.aop.XRayEnabled;

@Service("xrayExampleService")
@Transactional
@XRayEnabled
public class XrayExampleService {
	private static final Logger logger = LoggerFactory.getLogger(XrayExampleService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public String xrayDemoServiceMethod(String response) {
		logger.info("sleuth xray Demo : ################################################### " + response);
		return response;
	}

	public String xrayServiceMethod() {
		String response = restTemplate.exchange("http://springboot2.default:8080/xraydemo", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}).getBody();

		try {
			String exceptionrsp = restTemplate.exchange("http://springboot2.default:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
			}).getBody();
			response = response + " ================= " + exceptionrsp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("sleuth xray : ################################################### " + response);
		return response;
	}
}
