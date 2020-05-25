package com.mkudryavtsev.springapp.repository;

import com.mkudryavtsev.springapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Account}.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
