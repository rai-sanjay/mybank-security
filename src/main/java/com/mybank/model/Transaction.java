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
public class Transaction {
	@Id
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name="account_number")
	private long accountNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "customer_id")
	  private Customer customer;
	
	@Column(name="transaction_date")
	private Date transactionDt;
	
	@Column(name = "transaction_summary")
	private String transactionSummary;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name = "transaction_amt")
	private int transactionAmt;
	
	@Column(name = "closing_balance")
	private int closingBalance;
	
	@Column(name = "create_date")
	private String createDt;

}
