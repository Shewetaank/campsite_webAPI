package com.campsite.api;

import com.campsite.api.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class CampsiteWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampsiteWebApiApplication.class, args);
	}
}