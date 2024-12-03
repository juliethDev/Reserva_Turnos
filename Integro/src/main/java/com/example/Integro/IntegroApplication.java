package com.example.Integro;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example.Integro")
public class IntegroApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegroApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(IntegroApplication.class, args);


		LOGGER.info("ClinicaOdontologica is now running...");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
