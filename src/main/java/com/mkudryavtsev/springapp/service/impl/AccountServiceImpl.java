package com.mkudryavtsev.springapp.service.impl;

import com.mkudryavtsev.springapp.model.Account;
import com.mkudryavtsev.springapp.repository.AccountRepository;
import com.mkudryavtsev.springapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getById(Long id) {
        Account result = this.accountRepository.findById(id).orElse(null);
        if(Objects.isNull(result)) {
            log.warn("IN getById - no account found by id: {}", id);
            return null;
        }
        log.info("IN getById - account: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void save(Account account) {
        this.accountRepository.save(account);
        log.info("IN save - account : {} successfully saved", account);
    }

    @Override
    public void deleteById(Long id) {
        this.accountRepository.deleteById(id);
        log.info("IN deleteById - account with id: {} successfully deleted", id);
    }

    @Override
    public List<Account> getAll() {
        List<Account> result = this.accountRepository.findAll();
        log.info("IN getAll - {} accounts found", result.size());
        return result;
    }
}
