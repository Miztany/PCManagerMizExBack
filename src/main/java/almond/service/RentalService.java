package almond.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import almond.entity.Rental;
import almond.entity.User;
import almond.form.InventoryForm;
import almond.form.RentalForm;
import almond.form.ReturnForm;
import almond.repository.RentalRepository;
import almond.repository.UserRepository;
import almond.response.MessageResponse;

@Service
public class RentalService {

	@Autowired
	RentalRepository rentalRepository;

//	@Autowired
//	DeviceRepository deviceRepository;
//
	@Autowired
	UserRepository userRepository;

//	@Autowired
//	HistoryRepository historyRepository;

	public List<Rental> findUsable() {
		return rentalRepository.findUsable();
	}

	public List<Rental> findByRentalId(Integer rentalId) {
		return rentalRepository.findByRentalId(rentalId);
	}

	public MessageResponse rental(RentalForm rf) {
		List<User> users = userRepository.findByEmployeeNumAndDeleteFlagFalse(rf.getEmployeeNum());
		if ((users.size()) == 0) return new MessageResponse(false, "存在しないユーザーです");

		Rental r = rentalRepository.findByRentalId(rf.getRentalId()).get(0);
		r.setFree(false);
		r.setUser(users.get(0));
		r.setLoanDate(Date.valueOf(rf.getLoanDate()));
		r.setInventoryDate(Date.valueOf(rf.getInventoryDate()));
		r.setRemarks(rf.getRemarks());
		rentalRepository.save(r);

		return new MessageResponse(true, "");
	}

	public MessageResponse back(ReturnForm rf) {
		Rental r = rentalRepository.findByRentalId(rf.getRentalId()).get(0);
		r.setFree(true);
		r.setUser(null);
		r.setLoanDate(null);
		r.setRemarks(rf.getRemarks());
		rentalRepository.save(r);
		
		return new MessageResponse(true, "");
	}
	
	public MessageResponse inventory(InventoryForm inf) {
		Rental r = rentalRepository.findByRentalId(inf.getRentalId()).get(0);
		r.setInventoryDate(Date.valueOf(inf.getInventoryDate()));
		r.setRemarks(inf.getRemarks());
		rentalRepository.save(r);
		
		return new MessageResponse(true, "");
	}

}
