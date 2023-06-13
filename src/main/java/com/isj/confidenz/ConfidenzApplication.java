package com.isj.confidenz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfidenzApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfidenzApplication.class, args);
	}
}

@Controller
class HelloApp {
	@GetMapping("/")
	@ResponseBody
	public String hello() {
		return "good job";
	}
}
