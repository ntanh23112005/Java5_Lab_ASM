package poly.java5.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{
	Role findByIdEquals(String id);
}
