package com.ormjpa.study;

import com.ormjpa.study.post.PostListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryConfigTest {

    @Bean
    public PostListener postListener() {
        return new PostListener();
    }
}
