package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	
	@GetMapping(value = "contact")
	public String findContacts() {
		return "Contacts details from database";
	}

}
