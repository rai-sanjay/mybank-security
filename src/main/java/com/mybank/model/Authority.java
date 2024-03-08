package com.mybank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Authority{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "role_id")
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "customer_id")// works on both Java field name/column name
	private Customer customer;

}
