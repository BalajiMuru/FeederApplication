package com.feeder;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeederAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeederAppApplication.class, args);
	}

	@Bean
	public ModelMapper getModelmapper()
	{
		return new ModelMapper();
	}
}
