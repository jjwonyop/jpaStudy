/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    long countByTitleContains(String title);
}
