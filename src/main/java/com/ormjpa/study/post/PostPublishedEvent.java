package com.ormjpa.study.post;

import com.ormjpa.study.post.Post;
import org.springframework.context.ApplicationEvent;

public class PostPublishedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    private final Post post;
    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (Post) source;
    }

    public Post getPost() {
        return post;
    }
}
