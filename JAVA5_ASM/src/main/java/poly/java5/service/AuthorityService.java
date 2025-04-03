package poly.java5.service;

import poly.java5.entity.Authority;

public interface AuthorityService {
	void save(Authority authority);
	
	Authority findByUserUsername(String username);
}
