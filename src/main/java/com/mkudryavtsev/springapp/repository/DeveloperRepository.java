package com.mkudryavtsev.springapp.repository;

import com.mkudryavtsev.springapp.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Developer}.
 */
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
