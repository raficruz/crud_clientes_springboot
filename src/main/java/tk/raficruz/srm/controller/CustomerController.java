package tk.raficruz.srm.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tk.raficruz.srm.model.Customer;
import tk.raficruz.srm.model.Risk;
import tk.raficruz.srm.model.dto.CustomerDTO;
import tk.raficruz.srm.model.mapper.CustomerMapper;
import tk.raficruz.srm.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@Autowired
	private CustomerMapper mapper;

	@Transactional
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO save(@RequestBody CustomerDTO input) {
		Customer saved = service.save(mapper.toEntity(input));
		return mapper.toDTO(saved);
	}

	@Transactional
	@PutMapping("/set-credit-line/{id}")
	public CustomerDTO updateCreditLine(@PathVariable Long id, @RequestBody BigDecimal newValue) {
		Customer saved = service.changeCreditLine(id, newValue);
		return mapper.toDTO(saved);
	}

	@Transactional
	@PutMapping("/set-credit-risk/{id}")
	public CustomerDTO updateCreditRisk(@PathVariable Long id, @RequestBody String newValue) {
		Customer saved = service.changeCreditRisk(id, Risk.get(newValue));
		return mapper.toDTO(saved);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public CustomerDTO findOne(@PathVariable Long id) {
		Customer saved = service.findOne(id);
		return mapper.toDTO(saved);
	}

	@Transactional(readOnly = true)
	@GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<CustomerDTO> findAll( 
			@PageableDefault(sort = {"id"}, 
							 direction = Sort.Direction.ASC, 
							 page = 0, size = 5) Pageable pageable) {
		Page<Customer> customers = service.findAll(pageable);
		return mapper.toPage(customers, pageable);
	}
}
