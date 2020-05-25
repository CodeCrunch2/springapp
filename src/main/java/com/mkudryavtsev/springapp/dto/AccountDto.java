package com.mkudryavtsev.springapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mkudryavtsev.springapp.model.Account;
import lombok.Data;

/**
 * DTO class for {@link Account} class.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {
    private Long id;
    private String accountData;
}
