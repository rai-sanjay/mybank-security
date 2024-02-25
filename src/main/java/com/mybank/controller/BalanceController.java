package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class BalanceController {
	
	@GetMapping(value = "balance")
	public String findBalances() {
		return "balance details from database";
	}

}
