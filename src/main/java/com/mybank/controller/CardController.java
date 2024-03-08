package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
	
	@GetMapping(value = "card")
	public String findCards() {
		return "Cards details from database";
	}

}
