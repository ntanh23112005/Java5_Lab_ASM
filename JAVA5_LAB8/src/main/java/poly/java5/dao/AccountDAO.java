package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.model.Account;


public interface AccountDAO extends JpaRepository<Account, String> {
}