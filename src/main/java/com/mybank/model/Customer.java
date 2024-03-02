package com.mybank.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybank.constant.DatabaseConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = DatabaseConstant.CUSTOMER_ID)
	private Integer id;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String name;
	@Column(name = DatabaseConstant.MOBILE_NUMBER)
	private String mobileNumber;
	private String role;
	@Column(name = DatabaseConstant.CREATED_DATE)
	private LocalDate createdDate;
}
