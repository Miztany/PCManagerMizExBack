package almond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import almond.entity.Rental;
import almond.response.ContentResponse;
import almond.service.RentalService;

@RestController
@CrossOrigin
public class RentalController {

	@Autowired
	RentalService rentalService;

	@GetMapping("/getRentalList")
	public List<Rental> getRentalList() {
		return rentalService.findUsable();
	}

	@GetMapping("/getRentalDetail")
	public ContentResponse<Rental> getRentalDetail(@RequestParam("id") String rentalIdstr) {
		ContentResponse<Rental> crr = new ContentResponse<Rental>();
		if (rentalIdstr.equals("null")) {
			crr.setResult(false);
		} else {
			crr.setResult(true);
			Integer rentalId = Integer.parseInt(rentalIdstr);
			crr.setContent(rentalService.findByRentalId(rentalId).get(0));
		}
		return crr;
	}

//	
//	@GetMapping("/")
//	public String firstload(Model model) {
//		List<RentalBack> listRentalBack = rentalService.findByFailureFlase();
//		model.addAttribute("listRentalBack", listRentalBack);
//
//		List<Integer> EmptyNotEmpty = rentalService.getSummary();
//		model.addAttribute("EmptyNum", EmptyNotEmpty.get(0));
//		model.addAttribute("NotEmptyNum", EmptyNotEmpty.get(1));
//
//		return "rental";
//	}
//
//	@GetMapping("/detail")
//	@ResponseBody
//	public RentalBack detail(@RequestParam String assetNum) {
//		List<RentalBack> lrb = rentalService.findByAssetNum(assetNum);
//		return lrb.get(0);
//	}
//
//	@GetMapping("/userDetail")
//	@ResponseBody
//	public User userDetail(@RequestParam String employeeNum) {
//		List<User> lu = rentalService.userDetail(employeeNum);
//		return lu.size() == 0 ? new User() : lu.get(0);
//	}
//
//	@PostMapping("/rental")
//	@ResponseBody
//	public Status rental(@Validated @ModelAttribute("rental") RentalForm rentalForm, BindingResult bindingResult) {
//		Status s = new Status();
//		s.setResult(true);
//
//		if (bindingResult.hasErrors()) {
//			s.setMessage("入力内容に誤りがあります");
//			s.setResult(false);
//		} else {
//			rentalService.rental(rentalForm);
//		}
//
//		return s;
//
//	}

//	@PostMapping("/return")
//	@ResponseBody
//	public Status Back(@Validated @ModelAttribute("return") ReturnForm returnForm, BindingResult bindingResult) {
//		Status s = new Status();
//		s.setResult(true);
//		System.out.println(bindingResult);
//		System.out.println(returnForm.getReturn_date());
//
//		if (bindingResult.hasErrors()) {
//			s.setMessage("入力内容に誤りがあります");
//			s.setResult(false);
//		} else {
//			rentalService.back(returnForm);
//		}
//		return s;
//	}

//	@PostMapping("/setRemarks")
//	@ResponseBody
//	public Status serRemarks(@Validated @ModelAttribute("remarks") RemarksForm remarksForm,
//			BindingResult bindingResult) {
//		Status s = new Status();
//		s.setResult(true);
//
//		if (bindingResult.hasErrors()) {
//			s.setMessage("入力に誤りがあります");
//			s.setResult(false);
//		} else {
//			rentalService.setRemarks(remarksForm);
//		}
//
//		return s;
//	}

//	@GetMapping("/usableDevices")
//	@ResponseBody
//	public List<Device> usableDevices() {
//		return rentalService.findUsableDevices();
//	}
//
//	@PostMapping("/addDevice")
//	@ResponseBody
//	public Status addDevice(@Validated @ModelAttribute("addDevice") RemarksForm remarksForm,
//			BindingResult bindingResult) {
//		Status s = new Status();
//		s.setResult(true);
//
//		if (bindingResult.hasErrors()) {
//			s.setMessage("入力に誤りがあります");
//			s.setResult(false);
//		} else {
//			rentalService.addDevice(remarksForm);
//		}
//
//		return s;
//	}

}
