package poly.java5.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
	boolean existsByUserUsernameAndRoleId(String username, String roleId);
	
	Authority findByRoleId(String id);
	
	Authority findByUserUsername(String username);
}
