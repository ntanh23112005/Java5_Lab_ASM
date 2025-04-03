package poly.java5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.AuthorityDAO;
import poly.java5.entity.Authority;
import poly.java5.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDAO authorityDAO;
	
	@Override
	public void save(Authority authority) {
		authorityDAO.save(authority);
	}

	@Override
	public Authority findByUserUsername(String username) {
		return authorityDAO.findByUserUsername(username);
	}

}
