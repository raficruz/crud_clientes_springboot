package tk.raficruz.srm.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SRM_CUSTOMER")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

	private static final long serialVersionUID = 9007977590496298963L;

	@Id
	@SequenceGenerator(name="customer_pk_sequence",sequenceName="customer_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_pk_sequence")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "credit_line")
	private BigDecimal creditLine;

	@Enumerated(EnumType.STRING)
	@Column(name = "credit_risk")
	private Risk creditRisk;

	@Column(name = "interest_rate")
	private BigDecimal interestRate;

	@Column(name = "active", nullable = false)
	private Boolean active;
}
