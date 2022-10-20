package com.rest.webservices.restfulwebservices.users;

import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public User getUser (Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(
                        "User with Id:" + userId + " does not exist"
                )
        );
    }

    public User createUser (User user) {
        return userRepository.save(user);
    }

    public void deleteUser (Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException(
                    "User with Id:" + userId + " does not exist"
            );
        }
    }

}
