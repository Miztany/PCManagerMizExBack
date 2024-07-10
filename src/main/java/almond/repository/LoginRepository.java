package almond.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import almond.entity.Login;
import almond.entity.User;

public interface LoginRepository extends JpaRepository<Login, Integer>  {

	List<Login> findByUser(User User);

}
