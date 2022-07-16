package com.example.jwt3;

import com.example.jwt3.entity.Role;
import com.example.jwt3.entity.User;
import com.example.jwt3.respository.RoleRespository;
import com.example.jwt3.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;

@SpringBootTest
class Jwt3ApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired private RoleRespository roleRespository;
//
    @Test
    public void addRoleToUsery(){
        int userId =5;
        User user = userRepository.findById(userId).get();
       Role role = roleRespository.findById(2).get();
        user.getRoles().add(role);
        userRepository.save(user);
    }
    @Test
    void contextLoads() {
    }

}
