package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Loan;

public interface ILoanRepository extends JpaRepository<Loan, Integer>{
	
	List<Loan> findByCustomerIdOrderByStartDtDesc(Integer customerId);

}
