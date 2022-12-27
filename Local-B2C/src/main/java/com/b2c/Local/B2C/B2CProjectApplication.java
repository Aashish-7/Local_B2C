package com.b2c.Local.B2C;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableCaching @EnableAsync
public class B2CProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(B2CProjectApplication.class, args);
	}

}
