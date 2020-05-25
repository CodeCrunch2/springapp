package com.mkudryavtsev.springapp.rest;

import com.mkudryavtsev.springapp.dto.AccountDto;
import com.mkudryavtsev.springapp.mapper.AccountMapper;
import com.mkudryavtsev.springapp.model.Account;
import com.mkudryavtsev.springapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestControllerV1 {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountRestControllerV1(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account account = this.accountService.getById(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountDto accountDto = accountMapper.toDto(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> saveAccount(@RequestBody @Validated AccountDto accountDto) {
        if (accountDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account account = accountMapper.toEntity(accountDto);
        this.accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Validated AccountDto accountDto) {
        if (accountDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account account = accountMapper.toEntity(accountDto);
        this.accountService.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("id") Long id) {
        Account account = accountService.getById(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.accountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<Account> accounts = this.accountService.getAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AccountDto> accountDtos = accounts.stream().map(accountMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(accountDtos,HttpStatus.OK);
    }

}
