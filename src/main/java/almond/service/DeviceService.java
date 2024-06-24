package almond.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import almond.entity.Device;
import almond.entity.Rental;
import almond.form.DeviceForm;
import almond.repository.DeviceRepository;
import almond.repository.RentalRepository;
import almond.response.MessageResponse;

@Service
public class DeviceService {

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	RentalRepository rentalRepository;

	public List<Device> findByDeleteFlagFalse() {
		return deviceRepository.findByDeleteFlagFalse();
	}

	public List<Device> findByAssetNum(String assetNum) {
		return deviceRepository.findByAssetNum(assetNum);
	}

	public MessageResponse formatSave(DeviceForm df) {
		Device nd = formToEntity(df);
		deviceRepository.save(nd);
		return new MessageResponse(true, "");
	}

	public MessageResponse delete(DeviceForm df) {
		Device d = deviceRepository.findByAssetNum(df.getAssetNum()).get(0);
		d.setDeleteFlag(true);
		deviceRepository.save(d);
		Rental r = rentalRepository.findByDevice(d).get(0);
		r.setDeleteFlag(true);
		rentalRepository.save(r);
		return new MessageResponse(true, "");
	}

	public MessageResponse register(DeviceForm df) {
		MessageResponse result;
		if (deviceRepository.findByAssetNum(df.getAssetNum()).size() == 0) {
			Device nd = formToEntity(df);
			deviceRepository.save(nd);
			Rental nr = newRentalFromDevice(nd);
			System.out.println(nr);
			rentalRepository.save(nr);
			result = new MessageResponse(true, "");
		} else {
			result = new MessageResponse(false, "既に存在するIDです");
		}
		return result;
	}

	private Rental newRentalFromDevice(Device d) {
		Rental nr = new Rental();
		nr.setDeleteFlag(false);
		nr.setDevice(d);
		nr.setFree(true);
		nr.setInventoryDate(d.getUpdateDate());
		return nr;
	}

	private Device formToEntity(DeviceForm df) {
		Device nd = new Device();
		nd.setAssetNum(df.getAssetNum());
		nd.setCapacity(df.getCapacity());
		nd.setDeleteFlag(df.getDeleteFlag());
		nd.setEndDate(Date.valueOf(df.getEndDate()));
		nd.setFailure(df.getFailure());
		nd.setGraphicsBoard(df.getGraphicsBoard());
		nd.setMaker(df.getMaker());
		nd.setMemory(df.getMemory());
		nd.setOperatingSystem(df.getOperatingSystem());
		nd.setRegisterDate(Date.valueOf(df.getRegisterDate()));
		nd.setRemarks(df.getRemarks());
		nd.setStartDate(Date.valueOf(df.getStartDate()));
		nd.setStorageLocation(df.getStorageLocation());
		nd.setUpdateDate(Date.valueOf(df.getUpdateDate()));
		return nd;
	}

}
