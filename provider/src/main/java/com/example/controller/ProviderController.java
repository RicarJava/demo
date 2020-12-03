package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProviderController {

	@Value("${server.port}")
	private String port;

	@GetMapping(value = "/port")
	public String port() {
		return "My port: " + port;
	}

}
