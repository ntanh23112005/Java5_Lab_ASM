package poly.java5.service;

import java.util.List;

import poly.java5.entity.User;

public interface UserService {

	List<User> findAll();

	void deleteByUsername(String username);

	void create(User user);

	void update(User user);

	User findByUsername(String username);

}
