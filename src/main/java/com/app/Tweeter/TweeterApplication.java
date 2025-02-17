package com.app.Tweeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com")
@EntityScan("com.bean")
public class TweeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweeterApplication.class, args);
	}

}
