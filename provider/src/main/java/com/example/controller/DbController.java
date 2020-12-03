package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DbController {
	@Value("${datasource.url}")
	private String url;
	@Value("${datasource.username}")
	private String username;
	@Value("${datasource.password}")
	private String password;
	@Value("${datasource.driver-class}")
	private String driver;

	@GetMapping("/dbInfo")
	public String getDbInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("url: ").append(url).append(System.lineSeparator())
				.append("username: ").append(username).append(System.lineSeparator())
				.append("password: ").append(password).append(System.lineSeparator())
				.append("driver: ").append(driver);
		return sb.toString();
	}

}
