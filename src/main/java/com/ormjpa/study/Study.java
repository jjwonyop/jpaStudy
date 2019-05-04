/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of={"id", "name"})
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDY")
    @SequenceGenerator(sequenceName = "SEQ_STUDY", allocationSize = 1, name = "SEQ_STUDY")
    private Long id;

    private String name;

    @ManyToOne
    // 한개의 주인이 있으니
    // 만약 양방향? 서로 참조 하도록 한다면....
    // manytoOne + oneToMany 는 서로 연관관계 없는 2개의 단방향 관계
    private Account owner;

}
