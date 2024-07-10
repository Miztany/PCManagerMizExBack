package almond.requestBody;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginBody {
	@NotEmpty
	private String employeeNum;
	@NotEmpty
	private String password;
}
