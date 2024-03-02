package com.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Contact;

public interface IContactRepository extends JpaRepository<Contact, String>{

}
