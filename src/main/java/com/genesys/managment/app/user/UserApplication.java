package com.genesys.managment.app.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	/**
	 * @Bean
	 * @return
	 * public ModelMapper modelMapper() {
		return new ModelMapper();
		}
	 */
	

}
