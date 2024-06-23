package almond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import almond.entity.User;
import almond.response.ContentResponse;
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

//	@GetMapping("/user")
//	public String firstload(Model model) {
//		model.addAttribute("listUser", userService.findByDeleteFlagFalse());
//		return "user";
//	}
//	
//	@PostMapping("/user/edit")
//	@ResponseBody
//	public Status edit(@Validated @ModelAttribute("edit") UserForm userForm, BindingResult bindingResult) {
//		Status s = new Status();
//		s.setResult(true);
//
//		if (bindingResult.hasErrors()) {
//			s.setMessage("入力に誤りがあります");
//			System.out.println(bindingResult.getAllErrors());
//			s.setResult(false);
//		} else {
//			userService.edit(userForm);
//		}
//
//		return s;
//	}
//	
//	@GetMapping("/user/detail")
//	@ResponseBody
//	public User detail(@RequestParam String employeeNum) {
//		return userService.findByEmployeeNum(employeeNum).get(0);
//	}
//	
//	@GetMapping("/user/delete")
//	@ResponseBody
//	public void delete(@RequestParam String employeeNum) {
//		userService.delete(employeeNum);
//	}

}
