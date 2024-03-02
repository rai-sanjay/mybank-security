package com.mybank.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.model.Notice;
import com.mybank.repository.INoticeRepository;

@RestController
@RequestMapping(value = "/api/")
public class NoticeController {

	@Autowired
	private INoticeRepository noticeRepository;

	@GetMapping("notice")
	public ResponseEntity<List<Notice>> getNotices() {
		List<Notice> notices = noticeRepository.findAll();
		if (notices != null) {
			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(notices);
		} else {
			return null;
		}
	}

}
