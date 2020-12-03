package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Value("${service.provider-url}")
	private String serviceURL;

	// 这里注入的restTemplate就是在com.example.config.RestTemplateConfig中通过@Bean配置的实例
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/consumer/port")
	public String port() {
		return restTemplate.getForObject(serviceURL + "/port", String.class);
	}
}
