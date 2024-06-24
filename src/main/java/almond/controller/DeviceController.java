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

import almond.entity.Device;
import almond.form.DeviceForm;
import almond.response.ContentResponse;
import almond.response.MessageResponse;
import almond.service.DeviceService;

@RestController
@CrossOrigin
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@GetMapping("/getDeviceList")
	public List<Device> getDeviceList() {
		return deviceService.findByDeleteFlagFalse();
	}

	@GetMapping("/getDeviceDetail")
	public ContentResponse<Device> getDeviceDetail(@RequestParam("id") String assetNum) {
		ContentResponse<Device> crd = new ContentResponse<Device>();
		if (assetNum.equals("null")) return new ContentResponse<Device>(false, null);
		return new ContentResponse<Device>(true, deviceService.findByAssetNum(assetNum).get(0));
	}

	@PostMapping("/postDeviceSave")
	public MessageResponse postDeviceSave(@Validated @ModelAttribute("edit") DeviceForm df,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return deviceService.formatSave(df);
	}

	@PostMapping("/postDeviceDelete")
	public MessageResponse postDeviceDelete(@ModelAttribute("edit") DeviceForm df) {
		return deviceService.delete(df);
	}

	@PostMapping("/postDeviceRegister")
	public MessageResponse postDeviceRegister(@Validated @ModelAttribute("edit") DeviceForm df,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return new MessageResponse(false, "入力に誤りがあります");
		return deviceService.register(df);
	}
}
