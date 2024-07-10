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

import almond.entity.Rental;
import almond.form.InventoryForm;
import almond.form.RentalForm;
import almond.form.ReturnForm;
import almond.response.ContentResponse;
import almond.response.MessageResponse;
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
		if (rentalIdstr.equals("null")) return new ContentResponse<Rental>(false, null);
		return new ContentResponse<Rental>(true, rentalService.findByRentalId(Integer.parseInt(rentalIdstr)).get(0));
	}

	@PostMapping("/postRentalRental")
	public MessageResponse postRentalRental(@Validated @ModelAttribute("rental") RentalForm rf,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return rentalService.rental(rf);
	}

	@PostMapping("/postRentalReturn")
	public MessageResponse postRentalReturn(@Validated @ModelAttribute("return") ReturnForm rf,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return rentalService.back(rf);

	}
	
	@PostMapping("/postRentalInventory")
	public MessageResponse postRentalInventory(@Validated @ModelAttribute("inventory") InventoryForm inf,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return rentalService.inventory(inf);

	}

}
