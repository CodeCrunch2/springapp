package com.mkudryavtsev.springapp.service;

import com.mkudryavtsev.springapp.model.Account;

import java.util.List;

public interface AccountService {
    Account getById(Long id);
    void save(Account account);
    void deleteById(Long id);
    List<Account> getAll();
}
