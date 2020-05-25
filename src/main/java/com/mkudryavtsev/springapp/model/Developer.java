package com.mkudryavtsev.springapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Developer.
 */

@Entity
@Table(name = "developers")
@Data
public class Developer extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "speciality")
    private String speciality;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developer_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

}
