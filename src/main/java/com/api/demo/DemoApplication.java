package com.api.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.demo.service.CityService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final CityService cityService;

	public DemoApplication(CityService cityService) {
		this.cityService = cityService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cityService.syncCities();
	}

}
