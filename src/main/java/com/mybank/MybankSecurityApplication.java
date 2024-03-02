package com.mybank;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mybank.model.Customer;
import com.mybank.model.Notice;
import com.mybank.repository.ICustomerRepository;
import com.mybank.repository.INoticeRepository;

@SpringBootApplication
public class MybankSecurityApplication implements CommandLineRunner {

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	INoticeRepository noticeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MybankSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Customer> customers = customerRepository.findByEmail("rai.sanjay2015@outlook.com");
		if (null == customers || customers.isEmpty()) {
			Customer customer = new Customer();
			customer.setMobileNumber("9581704567");
			customer.setName("Sanjay Rai");
			customer.setEmail("rai.sanjay2015@outlook.com");
			customer.setPassword(passwordEncoder.encode("12345"));
			customer.setRole("ROOT");
			customer.setCreatedDate(LocalDate.now());
			Customer insertedCustomer = customerRepository.save(customer);
			System.out.println(insertedCustomer.getId());
		}
		List<Notice> notices = noticeRepository.findAll();
		if (null == notices || notices.isEmpty()) {
			Notice notice = new Notice();
			notice.setCreateDt(LocalDate.now());
			notice.setNoticBegDt(LocalDate.now());
			notice.setNoticeDetails(
					"Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately");
			notice.setNoticeSummary("Home Loan Interest rates reduced");
			notice.setNoticEndDt(LocalDate.now().plusDays(90l));
			Notice insertedNotice = noticeRepository.save(notice);
			System.out.println(insertedNotice.getNoticeId());
		}
	}

	// INSERT NOTICE

}
