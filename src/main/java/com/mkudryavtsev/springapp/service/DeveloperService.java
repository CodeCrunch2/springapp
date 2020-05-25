package com.mkudryavtsev.springapp.service;

import com.mkudryavtsev.springapp.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer getById(Long id);
    void save(Developer developer);
    void deleteById(Long id);
    List<Developer> getAll();
}
