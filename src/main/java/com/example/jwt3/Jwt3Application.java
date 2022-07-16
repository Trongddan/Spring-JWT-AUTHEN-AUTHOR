package com.example.jwt3;

import com.example.jwt3.entity.Role;
import com.example.jwt3.entity.User;
import com.example.jwt3.respository.RoleRespository;
import com.example.jwt3.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Jwt3Application {
    @Autowired
    private UserRepository userRepository;
    @Autowired private RoleRespository roleRespository;

//    @Bean
//    public  void createUser(){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password ="dan12345";
//        String endCoder = passwordEncoder.encode(password);
//        User user = new User("le@gmail.com",endCoder);
//        userRepository.save(user);
//    }
//    @Bean
//    public void createRole(){
//        Role admin = new Role("ROLE_ADMIN");
//        Role editor = new Role("ROLE_EDITOR");
//        Role customer = new Role("ROLE_CUSTOMER");
//        List<Role> list = new ArrayList<>();
//        list.add(admin);
//        list.add(editor);
//        list.add(customer);
//
//        roleRespository.saveAll(list);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(Jwt3Application.class, args);
    }

}
