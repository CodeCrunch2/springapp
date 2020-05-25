package com.mkudryavtsev.springapp.service.impl;

import com.mkudryavtsev.springapp.model.Skill;
import com.mkudryavtsev.springapp.repository.SkillRepository;
import com.mkudryavtsev.springapp.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill getById(Long id) {
        Skill result = this.skillRepository.findById(id).orElse(null);
        if(Objects.isNull(result)) {
            log.warn("IN getById - no skill found by id: {}", id);
            return null;
        }
        log.info("IN getById - skill: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void save(Skill skill) {
        this.skillRepository.save(skill);
        log.info("IN save - skill : {} successfully saved", skill);
    }

    @Override
    public void deleteById(Long id) {
        this.skillRepository.deleteById(id);
        log.info("IN deleteById - skill with id: {} successfully deleted", id);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = this.skillRepository.findAll();
        log.info("IN getAll - {} skills found", result.size());
        return result;
    }
}
