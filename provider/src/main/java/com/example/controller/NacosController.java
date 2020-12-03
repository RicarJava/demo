package com.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class NacosController {

	@GetMapping("/dd")
	public String dd() {
		Pattern pattern = Pattern.compile("(\\d+)\\s+(?!s)(\\w+)");
		String source = "543543   ttreets"; // 如"543543 streets" 匹配失败
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println("group " + i + ":" + matcher.group(i));
			}
		}
		return "aa";
	}

	@GetMapping("/cc")
	public String cc() {
		Pattern pattern = Pattern.compile("(\\d+)\\s+(?=s)(\\w+)");
		String source = "543543   streets"; // "543543 ttreets" 匹配失败
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println("group " + i + ":" + matcher.group(i));
			}
		}
		return "aa";
	}

	@GetMapping("/bb")
	public String ss() {
		Pattern pattern = Pattern.compile("(\\d+)\\s+(?>bc|b)(\\w)");
		String source = "543543   bcc"; // 而“543543 bc” 却匹配失败因为bc已经被原子分组匹配了，当(\\w)进行匹配的时候前面的分组由于是贪婪型匹配所以不会突出以匹配的字符
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println("group " + i + ":" + matcher.group(i));
			}
		}
		return "aa";
	}

	@GetMapping(value = "test2")
	public String aa() {
		Pattern pattern = Pattern.compile("(?:(\\d+))?\\s?([a-zA-Z]+)?.+");
		String source = "2133 fdsdee4333";
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println("group " + i + ":" + matcher.group(i));
			}
		}
		return "ss";
	}

	@GetMapping(value = "config")
	public String readConfig() throws IOException {
		// 读取配置文件内容
		File file = new File("E://aaa.config");
		byte[] bytes = new byte[1024];
		StringBuffer result = new StringBuffer();
		FileInputStream in = new FileInputStream(file);
		int len;
		while ((len = in.read(bytes)) != -1) {
			result.append(new String(bytes, 0, len));
		}
		String config = result.toString();

		// 正则表达式替换文本
//		String regex = "<page>(.*?)<version>(.*?)</version>(.*?)</page>";
//		String regex = "<version>(.*?)</version>";
		String regex = "<page>[\\s\\S]*?<version>(.*?)</version>[\\s\\S]*?</page>";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(config);
		String page = m.group(0);
		while (m.find()) {
			page = m.group(0);
		}
		String vRegex = "<version>(.*?)</version>";
		Pattern vPattern = Pattern.compile(vRegex);
		Matcher vm = vPattern.matcher(vRegex);
		page = page.replaceAll(vRegex, "<version>xxxxxxx</version>");
		// 然后替换
		config = config.replaceAll(regex, page);
		System.out.println(config);
		// 替换
//		config.replaceAll();
//		StringBuffer sb = new StringBuffer();
//		while (m.find()) {
//			// 将匹配之前的字符串复制到sb,再将匹配结果替换为："favour"，并追加到sb
//			String a = m.group(1);
//			// 替换文本
//			m.appendReplacement()；
//		}
//		System.out.println(sb.toString());
//		m.appendTail(sb);
//		System.out.println(sb.toString());

		// 保存
		return "success";
	}

}
