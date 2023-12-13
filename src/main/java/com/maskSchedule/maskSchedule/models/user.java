package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class user {


    @NotNull
    private String username;

    @NotNull
    private String pwHash;
    public user(){
        // Default constructor
    }
    public user(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean ismatchingPassword(String password){
        return encoder.matches(username,pwHash);
    }
    // Getters and setters
}
