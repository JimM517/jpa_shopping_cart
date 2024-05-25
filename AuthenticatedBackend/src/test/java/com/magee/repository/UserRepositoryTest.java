package com.magee.repository;

import com.magee.models.ApplicationUser;
import com.magee.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

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


    // added this test to see if we can add a user with a state code
    @Test
    public void addAnotherUser() {

        Role userRole = roleRepository.findByAuthority("USER").orElseGet(() -> roleRepository.save(new Role("USER")));



        ApplicationUser userWithStateCode = ApplicationUser.builder()
                .username("jboo")
                .password(passwordEncoder.encode("temple"))
                .stateCode("PA")
                .authorities(Collections.singleton(userRole))
                .build();



            userRepository.save(userWithStateCode);
    }






    /** just testing this, probably won't need this for the app **/
    @Test
    public void deleteUserInfo() {


        int oldUser = userRepository.deleteApplicationUserById(6l);

        Optional<ApplicationUser> deleted = userRepository.findById(Long.valueOf(oldUser));

        assertTrue(deleted.isEmpty());

    }



    @Test
    public void updateUserStateCode() {

        ApplicationUser user = userRepository.findById(3L).get();

        int updatedUser = userRepository.updateApplicationUserStateCodeByAppUserId("CA", user.getUserId());


        ApplicationUser updated = userRepository.findById(Long.valueOf(updatedUser)).get();

        userRepository.save(updated);



    }




}