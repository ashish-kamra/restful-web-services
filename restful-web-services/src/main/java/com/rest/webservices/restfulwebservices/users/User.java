package com.rest.webservices.restfulwebservices.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.webservices.restfulwebservices.posts.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long id;
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String name;
    @Past(message = "Birth Date should be in the past")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User (String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
