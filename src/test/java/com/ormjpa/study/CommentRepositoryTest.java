/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comments comments = new Comments();
        comments.setComments("Hello");
        commentRepository.save(comments);

        List<Comments> all = commentRepository.findAll();
        // jpa에서는 빈 collection을 return해줌
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);

        Optional<Comments> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();

    }

    @Test
    public void comments() {
        createComments(10, "Spring data jpa");
        createComments(100, "Hibernate Spring");

        List<Comments> commentsList = commentRepository.findByCommentsContainsIgnoreCase("Spring");
        assertThat(commentsList.size()).isEqualTo(2);

        List<Comments> spring = commentRepository.findByCommentsContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);
        assertThat(spring.size()).isEqualTo(1);

        List<Comments> orderByLikeCountDesc = commentRepository.findByCommentsContainsIgnoreCaseOrderByLikeCountDesc("Spring");
        assertThat(orderByLikeCountDesc.size()).isEqualTo(2);
        assertThat(orderByLikeCountDesc).first().hasFieldOrPropertyWithValue("likeCount", 100);

        try (Stream<Comments> spring1 = commentRepository.findByCommentsContainsIgnoreCaseOrderByLikeCountAsc("Spring")) {
            spring1.findFirst().ifPresent(a -> assertThat(a).hasFieldOrPropertyWithValue("likeCount", 10));
        }

    }

    private void createComments(int likeCount, String comment) {
        Comments comments = new Comments();
        comments.setComments(comment);
        comments.setLikeCount(likeCount);
        commentRepository.save(comments);
    }

}