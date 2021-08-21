package com.api.pipeline.blackup.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mem_id;
    @Column
    private String id;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private Date register_at;
    @Column
    private Date update_at;

}