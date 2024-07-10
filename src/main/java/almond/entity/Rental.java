package almond.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TRN_RENTAL")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private Integer rentalId;
	@OneToOne
	@JoinColumn(name = "asset_num")
	private Device device;
	@Column(name = "free")
	private Boolean free;
	@ManyToOne
	@JoinColumn(name = "employee_num")
	private User user;
	@Column(name = "loan_date")
	private Date loanDate;
	@Column(name = "inventory_date")
	private Date inventoryDate;
	@Column(name = "remarks")
	private String remarks;
	@Column(name="delete_flag")
	private Boolean deleteFlag; 
}
