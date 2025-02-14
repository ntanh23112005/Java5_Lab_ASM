package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.UserDAO;
import poly.java5.entity.User;
import poly.java5.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;
	
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteByUsername(String username) {
		dao.deleteById(username);
	}

	@Override
	public void create(User user) {
		dao.save(user);
	}

	@Override
	public void update(User user) {
		dao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return dao.getReferenceById(username);
	}

}
