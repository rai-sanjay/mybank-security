package com.mybank.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybank.constant.DatabaseConstant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DatabaseConstant.CUSTOMER_ID)
	private Integer id;
	private String email;
	//Allows only write operation - To DB. From DB is prohibited // Why not json ignore - vid 68
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String name;
	@Column(name = DatabaseConstant.MOBILE_NUMBER)
	private String mobileNumber;
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Authority> authorities;
	@Column(name = DatabaseConstant.CREATED_DATE)
	private LocalDate createdDate;
}
