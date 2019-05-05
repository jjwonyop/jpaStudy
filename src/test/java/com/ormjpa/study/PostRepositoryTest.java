/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    @Rollback(false)
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("Hello");

        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();

        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> pagePost = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(pagePost.getTotalElements()).isEqualTo(1);
        assertThat(pagePost.getNumber()).isZero();
        assertThat(pagePost.getSize()).isEqualTo(10);
        assertThat(pagePost.getNumberOfElements()).isEqualTo(1);

        // Then
        Page<Post> spring = postRepository.findByTitleContains("Hello", PageRequest.of(0, 10));
        assertThat(spring.getTotalElements()).isEqualTo(1);
        assertThat(spring.getNumber()).isZero();
        assertThat(spring.getSize()).isEqualTo(10);
        assertThat(spring.getNumberOfElements()).isEqualTo(1);

        // When
        long hello = postRepository.countByTitleContains("Hello");
        // Then
        assertThat(hello).isEqualTo(1);

    }

}