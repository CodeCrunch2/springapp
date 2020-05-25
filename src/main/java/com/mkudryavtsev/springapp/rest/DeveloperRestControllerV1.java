package com.mkudryavtsev.springapp.rest;


import com.mkudryavtsev.springapp.dto.DeveloperDto;
import com.mkudryavtsev.springapp.mapper.DeveloperMapper;
import com.mkudryavtsev.springapp.model.Developer;
import com.mkudryavtsev.springapp.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
    private final DeveloperService developerService;
    private final DeveloperMapper developerMapper;


    public DeveloperRestControllerV1(DeveloperService developerService, DeveloperMapper developerMapper) {
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperDto> getDeveloper(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Developer developer = this.developerService.getById(id);
        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DeveloperDto developerDto = developerMapper.toDto(developer);
        return new ResponseEntity<>(developerDto, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperDto> saveDeveloper(@RequestBody @Validated DeveloperDto developerDto) {
        if (developerDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Developer developer = developerMapper.toEntity(developerDto);
        this.developerService.save(developer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperDto> updateDeveloper(@RequestBody @Validated DeveloperDto developerDto) {
        if (developerDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Developer developer = developerMapper.toEntity(developerDto);
        this.developerService.save(developer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperDto> deleteDeveloper(@PathVariable("id") Long id) {
        Developer developer = developerService.getById(id);
        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.developerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeveloperDto>> getAllDevelopers() {
        List<Developer> developers = this.developerService.getAll();
        if (developers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DeveloperDto> developerDtos =
                developers
                        .stream()
                        .map(developerMapper::toDto)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(developerDtos,HttpStatus.OK);
    }
}