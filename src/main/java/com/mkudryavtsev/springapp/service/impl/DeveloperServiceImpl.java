package com.mkudryavtsev.springapp.service.impl;

import com.mkudryavtsev.springapp.model.Developer;
import com.mkudryavtsev.springapp.repository.DeveloperRepository;
import com.mkudryavtsev.springapp.service.DeveloperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer getById(Long id) {
        Developer result = this.developerRepository.findById(id).orElse(null);
        if(Objects.isNull(result)) {
            log.warn("IN getById - no developer found by id: {}", id);
            return null;
        }
        log.info("IN getById - developer: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void save(Developer developer) {
        this.developerRepository.save(developer);
        log.info("IN save - developer : {} successfully saved", developer);
    }

    @Override
    public void deleteById(Long id) {
        this.developerRepository.deleteById(id);
        log.info("IN deleteById - developer with id: {} successfully deleted", id);
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> result = this.developerRepository.findAll();
        log.info("IN getAll - {} developers found", result.size());
        return result;
    }
}
