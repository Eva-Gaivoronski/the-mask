package com.maskSchedule.maskSchedule.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginFormDTO {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 45, message = "Uesrname must be 5-45 characters long.")
    private String username;

    @NotNull(message = "password is required")
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 45, message = "password must be 6-45 characters long.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
