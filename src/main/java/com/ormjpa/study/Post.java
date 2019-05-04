/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @SequenceGenerator(sequenceName = "POST_SEQ", allocationSize = 1, name = "POST_SEQ")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comments> comments = new HashSet<>();
    public void addComment(Comments comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }
}
