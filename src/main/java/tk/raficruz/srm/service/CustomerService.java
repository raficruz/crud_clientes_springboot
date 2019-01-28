package tk.raficruz.srm.service;
import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tk.raficruz.srm.model.Customer;
import tk.raficruz.srm.model.Risk;

public interface CustomerService {
	Customer save(Customer c);
	Customer findOne(Long id);
	Page<Customer> findAll(Pageable pageable);
	Customer changeCreditLine(Long id, BigDecimal newValue);
	Customer changeCreditRisk(Long id, Risk newValue);
	BigDecimal calculateInterestRate(Risk risk);
}
