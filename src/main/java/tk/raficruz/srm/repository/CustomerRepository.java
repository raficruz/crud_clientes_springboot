package tk.raficruz.srm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tk.raficruz.srm.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Page<Customer> findByActiveTrue(Pageable pageable);
}
