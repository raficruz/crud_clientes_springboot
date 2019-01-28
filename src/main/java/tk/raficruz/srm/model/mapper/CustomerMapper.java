package tk.raficruz.srm.model.mapper;

import org.springframework.stereotype.Component;

import tk.raficruz.srm.model.Customer;
import tk.raficruz.srm.model.Risk;
import tk.raficruz.srm.model.dto.CustomerDTO;

@Component
public class CustomerMapper extends BaseMapper<Customer, CustomerDTO> {

	@Override
	public CustomerDTO toDTO(Customer entity) {
		return CustomerDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.creditLine(entity.getCreditLine())
				.creditRisk(entity.getCreditRisk().getDescription())
				.interestRate(entity.getInterestRate())
				.active(entity.getActive())
			.build();
	}

	@Override
	public Customer toEntity(CustomerDTO dto) {
		Customer c = Customer.builder()
				.id(dto.getId())
				.name(dto.getName())
				.creditLine(dto.getCreditLine())
				.creditRisk(Risk.get(dto.getCreditRisk()))
				.interestRate(dto.getInterestRate())
			.build();

		c.setActive(dto.getActive());

		return c; 
	}
}
