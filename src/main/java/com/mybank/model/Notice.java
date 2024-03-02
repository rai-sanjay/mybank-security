package com.mybank.model;

import java.time.LocalDate;

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
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notice_id")
	private Integer noticeId;

	@Column(name = "notice_summary")
	private String noticeSummary;

	@Column(name = "notice_details")
	private String noticeDetails;

	@Column(name = "notic_beg_date")
	private LocalDate noticBegDt;
	
	@Column(name = "notic_end_date")
	private LocalDate noticEndDt;
	
	@Column(name = "created_date")
	private LocalDate createDt;
	
	@Column(name = "updated_date")
	private LocalDate updateDt;

}
