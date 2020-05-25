package com.mkudryavtsev.springapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Simple domain object that represents application user.
 */

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;


    @Column(name = "password_change_date")
    private Date lastPasswordChangeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @PrePersist
    public void toCreate() {
        setCreated(new Date());
        setUpdated(new Date());
        setLastPasswordChangeDate(new Date());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdated(new Date());
    }

}
