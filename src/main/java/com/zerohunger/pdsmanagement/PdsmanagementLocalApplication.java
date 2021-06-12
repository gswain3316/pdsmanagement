package com.zerohunger.pdsmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PdsmanagementLocalApplication implements CommandLineRunner{

	@Autowired
	private OrderGrantRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(PdsmanagementLocalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

	
	
}
