package poly.java5.service;

import poly.java5.model.Account;

public interface AccountService {
    Account findByUsername(String username);
}