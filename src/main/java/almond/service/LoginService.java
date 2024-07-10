package almond.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import almond.entity.Login;
import almond.entity.User;
import almond.repository.LoginRepository;
import almond.repository.UserRepository;
import almond.requestBody.LoginBody;
import almond.response.ContentResponse;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	UserRepository userRepository;
	
	

	public ContentResponse<User> login(LoginBody loginBody) {
		
//		ログインを試行するユーザーを取得
		List<User> lu = userRepository.findByEmployeeNum(loginBody.getEmployeeNum());
		if(lu.size() == 0) return new ContentResponse<User>(false, null);
		User loginTryingUser = lu.get(0);
		
//		ユーザーの登録情報を取得
		List<Login> ll = loginRepository.findByUser(loginTryingUser);
		if(ll.size() == 0) return new ContentResponse<User>(false, null);
		if(!ll.get(0).getPassword().equals(loginBody.getPassword())) return new ContentResponse<User>(false, null);
		
		return new ContentResponse<User>(true, loginTryingUser);
		
	}

}
