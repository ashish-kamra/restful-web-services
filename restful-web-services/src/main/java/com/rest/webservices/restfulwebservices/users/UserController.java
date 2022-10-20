package com.rest.webservices.restfulwebservices.users;

import com.rest.webservices.restfulwebservices.posts.Post;
import com.rest.webservices.restfulwebservices.posts.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    private final PostService postService;

    @Autowired
    public UserController (UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public EntityModel<User> getUser (@PathVariable("userId") Long userId) {
        User user = userService.getUser(userId);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<User> addUser (@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser (@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping(path = "/posts")
    public List<Post> getAllPosts () {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{userId}/posts")
    public List<Post> getUserPosts (@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return postService.getPostsByUserId(userId);
    }

    @PostMapping(path = "{userId}/post")
    public ResponseEntity<Post> addUserPost (@PathVariable Long userId, @RequestBody Post post) {
        User user = userService.getUser(userId);
        post.setUser(user);
        Post savedPost = postService.addPost(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
