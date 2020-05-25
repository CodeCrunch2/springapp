package com.mkudryavtsev.springapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents Account.
 */

@Entity
@Table(name = "accounts")
@Data
public class Account extends BaseEntity {
    @Column(name = "account_data")
    private String accountData;
}
