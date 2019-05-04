/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import javax.persistence.*;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ")
    @SequenceGenerator(sequenceName = "COMMENTS_SEQ", allocationSize = 1, name = "COMMENT_SEQ")
    private Long id;

    private String comments;

    @ManyToOne
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
