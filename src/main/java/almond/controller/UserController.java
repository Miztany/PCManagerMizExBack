package almond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import almond.entity.User;
import almond.form.UserForm;
import almond.response.ContentResponse;
import almond.response.MessageResponse;
import almond.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getUserList")
	public List<User> getUserList() {
		return userService.findByDeleteFlagFalse();
	}

	@GetMapping("/getUserDetail")
	public ContentResponse<User> getUesrDetail(@RequestParam("id") String employeeNum) {
		if (employeeNum.equals("null")) new ContentResponse<User>(false, null);
		return new ContentResponse<User>(true, userService.findByEmployeeNum(employeeNum).get(0));
	}

	@PostMapping("/postUserSave")
	public MessageResponse postUsereSave(@Validated @ModelAttribute("edit") UserForm uf, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return userService.formatSave(uf);
	}

	@PostMapping("/postUserDelete")
	public MessageResponse postUserDelete(@ModelAttribute("edit") UserForm uf) {
		return userService.delete(uf);
	}

	@PostMapping("/postUserRegister")
	public MessageResponse postUserRegister(@Validated @ModelAttribute("edit") UserForm uf,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return userService.register(uf);
	}

}
