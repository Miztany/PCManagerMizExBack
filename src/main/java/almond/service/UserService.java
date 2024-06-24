package almond.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import almond.entity.User;
import almond.form.UserForm;
import almond.repository.UserRepository;
import almond.response.MessageResponse;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<User> findByDeleteFlagFalse() {
		return userRepository.findByDeleteFlagFalse();
	}
	
	public List<User> findByEmployeeNum(String employeeNum) {
		return userRepository.findByEmployeeNum(employeeNum);
	}
	
	public MessageResponse formatSave(UserForm uf) {
		User nu = formToEntity(uf);
		userRepository.save(nu);
		return new MessageResponse(true, "");
	}

	public MessageResponse delete(UserForm uf) {
		User u = userRepository.findByEmployeeNum(uf.getEmployeeNum()).get(0);
		u.setDeleteFlag(true);
		userRepository.save(u);
		return new MessageResponse(true, "");
	}

	public MessageResponse register(UserForm uf) {
		if (userRepository.findByEmployeeNum(uf.getEmployeeNum()).size() == 0) {
			User nu = formToEntity(uf);
			userRepository.save(nu);
			return new MessageResponse(true, "");
		} else {
			return new MessageResponse(false, "既に存在するIDです");
		}
	}

	private User formToEntity(UserForm uf) {
		User nu = new User();
		nu.setAccountLevel(uf.getAccountLevel());
		nu.setAge(uf.getAge());
		nu.setDeleteFlag(uf.getDeleteFlag());
		nu.setDepartment(uf.getDepartment());
		nu.setEmployeeNum(uf.getEmployeeNum());
		nu.setGender(uf.getGender());
		nu.setMailAddress(uf.getMailAddress());
		nu.setName(uf.getName());
		nu.setNameKana(uf.getNameKana());
		nu.setPosition(uf.getPosition());
		nu.setRegisterDate(Date.valueOf(uf.getRegisterDate()));
		nu.setTelNum(uf.getTelNum());
		nu.setUpdateDate(Date.valueOf(uf.getUpdateDate()));
		nu.setRetireDate(uf.getRetireDate() == null ? null : Date.valueOf(uf.getRetireDate()));
		return nu;
	}
}
