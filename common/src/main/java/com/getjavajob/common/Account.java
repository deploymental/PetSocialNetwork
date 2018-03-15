package com.getjavajob.common;

import com.getjavajob.common.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String middleName;
    private String surName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(insertable = false)
    private Boolean isAdmin;
    private Date birthDate;
    private String homeAddress;
    private String workAddress;
    private String email;
    private String password;
    private String icq;
    private String skype;
    private String additionalInfo;
    private byte[] image;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Phone> phones;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Group> groups;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Friend> sender;
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Friend> recipient;
}