package com.rest.webservices.restfulwebservices.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.webservices.restfulwebservices.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    public Post (String description) {
        this.description = description;
    }

}
