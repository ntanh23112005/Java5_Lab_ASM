package poly.java5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.User;

public interface UserDAO extends JpaRepository<User, String> {

	User findByUsernameEquals(String username);
	
	User findByActivationtoken(String token);
	
	List<User> findAll();

}
