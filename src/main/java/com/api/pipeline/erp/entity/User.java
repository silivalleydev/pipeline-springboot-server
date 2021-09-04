package com.api.pipeline.erp.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", length = 60, unique = true)
    private String userId;

    @JsonIgnore
    @Column(name = "user_seq", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSeq;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "company", length = 30)
    private String company;

    @Column(name = "department", length = 30)
    private String department;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Column(name = "update_date")
    private Date updateDate;


    @ManyToMany
    @JoinTable(
            name = "authority",
            joinColumns = {@JoinColumn(name = "auth_id", referencedColumnName = "auth_id")},
            inverseJoinColumns = {@JoinColumn(name = "auth_id", referencedColumnName = "auth_id")})
    private Set<Authority> authorities;
}
