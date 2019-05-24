package com.ormjpa.study.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable("id") Post post) {
        // domain class Converter
        return post;
    }
}
