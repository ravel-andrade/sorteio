package com.dell.sorteio;

import lombok.AllArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SorteioApplication {
	public static void main(String[] args) {
		SpringApplication.run(SorteioApplication.class, args);
	}

}
