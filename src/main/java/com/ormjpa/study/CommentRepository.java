/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comments, Long> {

    // Query 만드는 방법
    // 1. 메소드 이름 분석해서 쿼리 만들기
    // 2. 미리 정의해 둔 쿼리 찾아 사용하기
    // 3. 미리 정의한 쿼리 찾아보고 없으면 만들기

    // 1. 메소드 이름 분석해서 쿼리 만들기
//    List<Comments> findByTitleContains(String keyword);

    // 2. 미리 정의해 둔 쿼리 찾아 사용하기(JQL default)
//    @Query(value = "SELECT c FROM Comments AS c", nativeQuery = true)
//    List<Comments> findByTitleContains(String keyword);

    // 3. 미리 정의한 쿼리 찾아보고 없으면 만들기(Create if not found)
    List<Comments> findByTitleContains(String keyword);

    Page<Comments> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    List<Comments> findByCommentsContainsIgnoreCase(String keyword);

    List<Comments> findByCommentsContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comments> findByCommentsContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    Stream<Comments> findByCommentsContainsIgnoreCaseOrderByLikeCountAsc(String keyword);
}