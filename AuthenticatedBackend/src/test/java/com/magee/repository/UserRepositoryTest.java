package com.magee.repository;

import com.magee.models.ApplicationUser;
import com.magee.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void addAppUser() {

        // here we are adding a new role of USER if it doesn't exist
        Role userRole = roleRepository.findByAuthority("USER").orElseGet(() -> roleRepository.save(new Role("USER")));

        // create the new user
        ApplicationUser user = ApplicationUser.builder()
                .username("user")
                .password(passwordEncoder.encode("newPass"))
                .authorities(Collections.singleton(userRole))
                .build();


        userRepository.save(user);


    }

}