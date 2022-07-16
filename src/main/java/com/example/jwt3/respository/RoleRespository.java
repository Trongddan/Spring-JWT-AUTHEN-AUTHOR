package com.example.jwt3.respository;

import com.example.jwt3.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRespository extends JpaRepository<Role,Integer> {
}
