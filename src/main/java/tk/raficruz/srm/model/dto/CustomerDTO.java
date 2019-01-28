package tk.raficruz.srm.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 3911732980723007334L;

	private Long id;
	private String name;
	private BigDecimal creditLine;
	private String creditRisk;
	private BigDecimal interestRate;
	private Boolean active;

}
