package com.mkudryavtsev.springapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents skill.
 */

@Entity
@Table(name = "skills")
@Data
public class Skill extends BaseEntity {
    @Column(name = "name")
    private String name;
}
