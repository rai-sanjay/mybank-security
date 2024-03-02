package com.mybank.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Notice {
	@Id
	@Column(name = "notice_id")
	private Integer noticeId;

	@Column(name = "notice_summary")
	private String noticeSummary;

	@Column(name = "notice_details")
	private String noticeDetails;

	@Column(name = "notic_beg_date")
	private Date noticBegDt;
	
	@Column(name = "notic_end_date")
	private Date noticEndDt;
	
	@Column(name = "created_date")
	private Date createDt;
	
	@Column(name = "updated_date")
	private Date updateDt;

}
