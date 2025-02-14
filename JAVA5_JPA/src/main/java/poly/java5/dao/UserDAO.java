package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.User;

public interface UserDAO extends JpaRepository<User, String>{

	
}
