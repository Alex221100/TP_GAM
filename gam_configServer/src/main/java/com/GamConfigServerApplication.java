package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableConfigServer
@SpringBootApplication
public class GamConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamConfigServerApplication.class, args);
	}

}
