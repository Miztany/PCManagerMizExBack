package almond.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReturnForm {
	@NotEmpty
	private Integer rentalId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate returnDate;

}
