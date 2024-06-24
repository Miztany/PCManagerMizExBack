package almond.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryForm {
	@NotEmpty
	private Integer rentalId;
	@NotNull
	private LocalDate inventoryDate;
}
