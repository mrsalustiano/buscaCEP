package com.marcelo.buscaCEP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.marcelo.buscaCEP")
public class BuscaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscaCepApplication.class, args);
	}

}
