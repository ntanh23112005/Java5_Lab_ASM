package poly.java5.service;

import java.util.List;

import poly.java5.entity.Authority;
import poly.java5.entity.Role;
import poly.java5.entity.User;

public interface UserService {

	User findByUsername(String username);
	
	void save(User user);
	
	User findByActivationToken(String token);
	
	List<User> findAll();
	
	void save(Authority authority);
	
	Role findByIdEquals(String id);

	void delete(String username);
}
