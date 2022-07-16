package com.example.jwt3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true,length = 50)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
