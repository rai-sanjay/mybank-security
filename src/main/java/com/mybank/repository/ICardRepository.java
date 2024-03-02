package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Card;

public interface ICardRepository extends JpaRepository<Card, Integer> {
	
	List<Card> findByCustomerId(int customerId);

}
