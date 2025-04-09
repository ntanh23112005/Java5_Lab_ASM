package poly.java5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.AccountDAO;
import poly.java5.model.Account;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account findByUsername(String username) {
        return accountDAO.findById(username).orElse(null);
    }
}