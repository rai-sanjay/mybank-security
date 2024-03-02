package com.mybank.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Loan {
	
	@Id
	@Column(name = "loan_number")
	private Integer loanNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "customer_id")
	  private Customer customer;
	
	@Column(name="start_date")
	private Date startDt;
	
	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "total_loan")
	private int totalLoan;
	
	@Column(name = "amount_paid")
	private int amountPaid;
	
	@Column(name = "outstanding_amount")
	private int outstandingAmount;
	
	@Column(name = "create_date")
	private String createDt;

}
