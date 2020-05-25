package com.mkudryavtsev.springapp.repository;

import com.mkudryavtsev.springapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
