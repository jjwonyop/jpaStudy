/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of={"id", "password"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACNT_SEQ")
    @SequenceGenerator(sequenceName = "ACNT_SEQ", allocationSize = 1, name = "ACNT_SEQ")
    private Long id;
    private String username;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient
    private String yes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "state", column = @Column(name = "home_state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "office_street")),
            @AttributeOverride(name = "city", column = @Column(name = "office_city")),
            @AttributeOverride(name = "state", column = @Column(name = "office_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "office_zipCode"))
    })
    private Address officeAddress;

//    @OneToMany(mappedBy = "owner")
    // 한 어카운트는 많은 스터디s
    // 끝이 Many는 Set
    // 양방향 할려면 oneTomany쪽에서 mappedBy로 매핑 해야 양방향 정의
    // 주인한테 관계를 설정해야됨
    @OneToMany(mappedBy = "owner")
    // cascade : 엔티티 상태변화 전파
    // 엔티티 상태는 4가지임 : transient, persistent, removed, detached
    // persistent -> 저장상태 JPA가 아는 상태로 됨, DB에 바로 save했다고 되는 것은 아님--> 더이상 변화될 것이 없다면 DB에 동기화 판단 후 DB저장(JPA, Hibernate가 판단)
    private Set<Study> studies = new HashSet<>();

    public void addStudy(Study study) {
        studies.add(study);
    }
}
