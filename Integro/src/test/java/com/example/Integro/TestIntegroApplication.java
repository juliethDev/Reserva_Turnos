package com.example.Integro;

import org.springframework.boot.SpringApplication;

public class TestIntegroApplication {

	public static void main(String[] args) {
		SpringApplication.from(IntegroApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
