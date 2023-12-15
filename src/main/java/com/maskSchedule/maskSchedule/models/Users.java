package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Users {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;
    public Users(){
        // Default constructor
    }
    public Users(String username, String password) {
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
}
