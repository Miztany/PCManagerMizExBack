package almond.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeviceForm {
	@NotBlank
	@Size(min = 1, max = 10)
	private String assetNum;
	@NotBlank
	@Size(min = 1, max = 20)
	private String maker;
	@NotBlank
	@Size(min = 1, max = 20)
	private String operatingSystem;
	@NotNull
	private Integer memory;
	@NotBlank
	@Size(min = 1, max = 5)
	private String capacity;
	@NotNull
	private Boolean graphicsBoard;
	@NotBlank
	@Size(min = 1, max = 30)
	private String storageLocation;
	@NotNull
	private Boolean failure;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate registerDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate updateDate;
	private String remarks;
	private Boolean deleteFlag;
}
