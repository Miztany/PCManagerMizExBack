package almond.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryForm {
	@NotNull
	private Integer rentalId;
	@NotNull
	private LocalDate inventoryDate;
}
