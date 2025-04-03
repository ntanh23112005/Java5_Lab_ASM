package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.java5.entity.User;
import poly.java5.test.UserTest;

public interface AccountDAO extends JpaRepository<User, String>{
	
	@Query("SELECT u FROM User u WHERE username = ?1 AND password = ?2 AND enabled = true")
	User findByUsernameAndPassword(String un, String pw);
	
	
	@Query("SELECT u FROM User u WHERE username = ?1 AND password = ?2 AND enabled = true")
	UserTest findByUsernameAndPasswordTest(String un, String pw);
}
