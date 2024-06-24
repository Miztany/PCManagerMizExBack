package almond.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalForm {
	@NotNull
	private Integer rentalId;
	@NotEmpty
	private String employeeNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate loanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate inventoryDate;
	private String remarks;
}
