package com.cinema.booker;

import com.cinema.booker.service.DataImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CinemaBookerApplication implements CommandLineRunner {

	@Value("${app.should-init-postcode-data:false}")
	private boolean shouldInitPostcodeData;

	private final DataImportService dataImportService;

	public static void main(String[] args) {
		SpringApplication.run(CinemaBookerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (shouldInitPostcodeData) {
			final Instant start = Instant.now();
			dataImportService.importTimeSlotData();
			final Instant finish = Instant.now();

			final long timeElapsed = Duration.between(start, finish).toSeconds();
			log.info("Time elapsed: {} sec", timeElapsed);
		}

	}
}
