package com.mkudryavtsev.springapp.rest;


import com.mkudryavtsev.springapp.dto.SkillDto;
import com.mkudryavtsev.springapp.mapper.SkillMapper;
import com.mkudryavtsev.springapp.model.Skill;
import com.mkudryavtsev.springapp.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillRestControllerV1 {
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    public SkillRestControllerV1(SkillService skillService, SkillMapper skillMapper) {
        this.skillService = skillService;
        this.skillMapper = skillMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> getSkill(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill skill = this.skillService.getById(id);
        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SkillDto skillDto = skillMapper.toDto(skill);
        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> saveSkill(@RequestBody @Validated SkillDto skillDto) {
        if (skillDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillMapper.toEntity(skillDto);
        this.skillService.save(skill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> updateSkill(@RequestBody @Validated SkillDto skillDto) {
        if (skillDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillMapper.toEntity(skillDto);
        this.skillService.save(skill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> deleteSkill(@PathVariable("id") Long id) {
        Skill skill = skillService.getById(id);
        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.skillService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        List<Skill> skills = this.skillService.getAll();
        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<SkillDto> skillDtos = skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(skillDtos,HttpStatus.OK);
    }

}
