package com.mkudryavtsev.springapp.repository;

import com.mkudryavtsev.springapp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Skill}.
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
