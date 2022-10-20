package com.rest.webservices.restfulwebservices.posts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PostConfig {
    @Bean
    CommandLineRunner commandLineRunner1 (PostRepository postRepository) {
        return args -> {
            Post post1 = new Post(
                    "First Post"
            );
            Post post2 = new Post(
                    "Second Post"
            );
            postRepository.saveAll(List.of(post1, post2));
        };
    }
}

