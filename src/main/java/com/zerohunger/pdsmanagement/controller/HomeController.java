package com.zerohunger.pdsmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		return "Welcome to the PDS App Home Version !";
	}
	
}
