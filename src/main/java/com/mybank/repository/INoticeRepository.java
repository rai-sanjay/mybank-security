package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Notice;

public interface INoticeRepository extends JpaRepository<Notice, Integer> {

	List<Notice> findAll();
}
