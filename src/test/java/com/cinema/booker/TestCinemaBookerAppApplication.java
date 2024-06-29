package com.cinema.booker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCinemaBookerAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(CinemaBookerApplication::main).with(TestCinemaBookerAppApplication.class).run(args);
	}

}
