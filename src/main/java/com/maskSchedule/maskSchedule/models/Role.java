package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    //    @NotNull
//    @NotBlank(message = "ShortHand is required!")
//    @Size(min = 1, max = 3, message = "ShortHand must be between 1 and 3 characters!")
    private char shortHand;
    @ManyToMany(mappedBy = "role")
    private final List<Employee> employee = new ArrayList<>();

    // Constructor
    public Role () {}

    public Role(String name) {
        this.name = name;
    }

    // Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getShortHand() {
        this.name = name.toUpperCase();
        this.shortHand = name.charAt(0);
        return shortHand;
    }
}
