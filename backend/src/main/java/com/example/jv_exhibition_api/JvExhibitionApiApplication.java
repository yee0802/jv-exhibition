package com.example.jv_exhibition_api;

import com.example.jv_exhibition_api.repository.ArtworkRepository;
import com.example.jv_exhibition_api.service.ArtworkIngestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JvExhibitionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvExhibitionApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner ingestOnStartup(ArtworkIngestionService ingestionService, ArtworkRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				ingestionService.ingestFromAllSources();
			}
		};
	}
}
