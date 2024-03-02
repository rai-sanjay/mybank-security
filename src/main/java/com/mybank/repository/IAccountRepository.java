package com.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
	Account findByCustomerId(int customerId);

}
