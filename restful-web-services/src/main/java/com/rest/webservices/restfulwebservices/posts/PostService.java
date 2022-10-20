package com.rest.webservices.restfulwebservices.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService (PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts () {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUserId (Long id) {
        return postRepository.findByUserId(id);
    }

    public Post addPost (Post post) {
        return postRepository.save(post);
    }
}
