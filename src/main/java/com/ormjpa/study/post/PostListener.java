package com.ormjpa.study.post;

import org.springframework.context.ApplicationListener;

public class PostListener implements ApplicationListener<PostPublishedEvent> {
    @Override
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("---------------------------");
        System.out.println("event published " + event.getPost());
    }
}
