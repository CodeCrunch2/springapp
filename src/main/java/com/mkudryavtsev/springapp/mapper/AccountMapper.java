package com.mkudryavtsev.springapp.mapper;

import com.mkudryavtsev.springapp.dto.AccountDto;
import com.mkudryavtsev.springapp.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Account toEntity(AccountDto accountDto) {
        return Objects.isNull(accountDto) ? null : modelMapper.map(accountDto, Account.class);
    }

    public AccountDto toDto(Account account) {
        return Objects.isNull(account) ? null : modelMapper.map(account, AccountDto.class);
    }
}
