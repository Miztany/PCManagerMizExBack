package almond.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

	@NotBlank
	@Size(min = 1, max = 10)
	private String employeeNum;
	@NotBlank
	@Size(min = 1, max = 20)
	private String name;
	@NotBlank
	@Size(min = 1, max = 20)
	private String nameKana;
	@NotBlank
	@Size(min = 1, max = 20)
	private String department;
	@NotBlank
	@Size(min = 1, max = 20)
	private String telNum;
	@NotBlank
	@Email
	@Size(min = 1, max = 50)
	private String mailAddress;
	@NotNull
	private Integer age;
	@NotNull
	private Integer gender;
	@NotBlank
	@Size(min = 1, max = 20)
	private String position;
	@NotNull
	private String accountLevel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate registerDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate updateDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate retireDate;
	@NotNull
	private Boolean deleteFlag;

}
