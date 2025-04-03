package poly.java5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.AccountDAO;
import poly.java5.entity.User;
import poly.java5.service.AccountService;
import poly.java5.test.UserTest;
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO dao;

	@Override
	public User findByUsernameAndPassword(String un, String pw) {
		return dao.findByUsernameAndPassword(un, pw);
	}

}
