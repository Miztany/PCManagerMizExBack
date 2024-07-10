package almond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import almond.entity.User;
import almond.requestBody.LoginBody;
import almond.response.ContentResponse;
import almond.service.LoginService;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public ContentResponse<User> login(@RequestBody LoginBody loginBody) {
		return loginService.login(loginBody); 
	}
}
