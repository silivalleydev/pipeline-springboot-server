package com.api.pipeline.member;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegister_at(Date register_at) {
        this.register_at = register_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
