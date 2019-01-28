package tk.raficruz.srm.service.impl;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tk.raficruz.srm.SrmException;
import tk.raficruz.srm.model.Customer;
import tk.raficruz.srm.model.Risk;
import tk.raficruz.srm.repository.CustomerRepository;
import tk.raficruz.srm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer save(Customer c) {
		c.setInterestRate(calculateInterestRate(c.getCreditRisk()));
		c.setActive(Optional.ofNullable(c.getActive()).orElse(Boolean.TRUE));
		return repository.save(c);
	}

	@Override
	public Customer findOne(Long id) {
		Optional<Customer> saved = repository.findById(id);
		if(saved.isPresent()) {
			return saved.get();
		}
		throw new SrmException(HttpStatus.NOT_FOUND, "Nenhum cliente encontrado com o id = " + id);
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return repository.findByActiveTrue(pageable);
	}

	@Override
	public Customer changeCreditLine(Long id, BigDecimal newValue) {
		Customer c = findOne(id);
		try {
			if (Optional.ofNullable(c).isPresent()) {
				c.setCreditLine(newValue);
				return repository.save(c);
			} else {
				throw new Exception("Não foi possível atualizar o limite de crédito!");
			}
		} catch (Exception e) {
			LOGGER.error("Houve um problema ao realizar a ação:", e);
		}
		return null;
	}

	@Override
	public Customer changeCreditRisk(Long id, Risk newValue) {
		Customer c = findOne(id);
		try {
			if (Optional.ofNullable(c).isPresent()) {
				c.setCreditRisk(newValue);
				c.setInterestRate(calculateInterestRate(newValue));
				return repository.save(c);
			} else {
				throw new Exception("Não foi possível atualizar o nível de risco!");
			}
		} catch (Exception e) {
			LOGGER.error("Houve um problema ao realizar a ação:", e);
		}
		return null;
	}

	@Override
	public BigDecimal calculateInterestRate(Risk risk) {
		if(Optional.ofNullable(risk).isPresent()) {
			switch(risk) {
				default:{
					return new BigDecimal(5);
				}
				case B:{
					return new BigDecimal(10);
				}
				case C:{
					return new BigDecimal(20);
				}
			}
		}
		return new BigDecimal(20);
	}

}
