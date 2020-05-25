package com.mkudryavtsev.springapp.service;

import com.mkudryavtsev.springapp.model.Skill;

import java.util.List;

public interface SkillService {
    Skill getById(Long id);
    void save(Skill skill);
    void deleteById(Long id);
    List<Skill> getAll();
}
