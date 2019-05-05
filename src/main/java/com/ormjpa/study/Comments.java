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
@EqualsAndHashCode(of = {"id", "comments"})
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ")
    @SequenceGenerator(sequenceName = "COMMENTS_SEQ", allocationSize = 1, name = "COMMENTS_SEQ")
    private Long id;

    private String comments;

    @ManyToOne
    private Post post;

    private String title;

    private Integer likeCount;
}
