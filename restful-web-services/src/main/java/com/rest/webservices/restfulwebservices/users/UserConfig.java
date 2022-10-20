package com.rest.webservices.restfulwebservices.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class UserConfig {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        httpSecurity.httpBasic(withDefaults());
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    CommandLineRunner commandLineRunner (UserRepository userRepository) {
        return args -> {
            User Ashish = new User(
                    "Ashish",
                    LocalDate.of(1998, 5, 21)
            );
            User Abhishek = new User(
                    "Abhishek",
                    LocalDate.of(1997, 6, 11)
            );

            userRepository.saveAll(List.of(Ashish, Abhishek));
        };
    }
}
