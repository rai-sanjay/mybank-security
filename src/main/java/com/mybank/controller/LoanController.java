package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class LoanController {
	
	@GetMapping(value = "loan")
	public String findLoans() {
		return "Loans details from database";
	}

}
