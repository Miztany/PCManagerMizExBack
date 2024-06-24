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
		ContentResponse<User> cru = new ContentResponse<User>();
		if(employeeNum.equals("null")) {
			cru.setResult(false);
		}else {
			cru.setResult(true);
			cru.setContent(userService.findByEmployeeNum(employeeNum).get(0));
		}
		return cru;
	}
	
	@PostMapping("/postUserSave")
	public MessageResponse postUsereSave(@Validated @ModelAttribute("edit") UserForm uf,
			BindingResult bindingResult) {
		MessageResponse mr;
		if (bindingResult.hasErrors()) {
			mr = new MessageResponse(false, "入力に誤りがあります");
		} else {
			mr = userService.formatSave(uf);
		}
		return mr;
	}

	@PostMapping("/postUserDelete")
	public MessageResponse postUserDelete(@ModelAttribute("edit") UserForm uf) {
		return userService.delete(uf);
	}

	@PostMapping("/postUserRegister")
	public MessageResponse postUserRegister(@Validated @ModelAttribute("edit") UserForm uf,
			BindingResult bindingResult) {
		MessageResponse mr;
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			mr = new MessageResponse(false, "入力に誤りがあります");
		} else {
			mr = userService.register(uf);
		}
		return mr;
	}

}
