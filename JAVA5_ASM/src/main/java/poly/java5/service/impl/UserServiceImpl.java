package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.AuthorityDAO;
import poly.java5.dao.RoleDAO;
import poly.java5.dao.UserDAO;
import poly.java5.entity.Authority;
import poly.java5.entity.Role;
import poly.java5.entity.User;
import poly.java5.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	@Autowired
	AuthorityDAO authorityDAO;
	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsernameEquals(username);
	}

	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public User findByActivationToken(String token) {
		return userDAO.findByActivationtoken(token);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public void save(Authority authority) {
		authorityDAO.save(authority);
	}

	@Override
	public Role findByIdEquals(String id) {
		return roleDAO.findByIdEquals(id);
	}

}
