package com.l.dev.payload.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class SingUpRequest {

    public static final int MIN_NAME_SIZE = 4;
    public static final int MAX_NAME_SIZE = 20;
    public static final int MIN_USERNAME_SIZE = 3;
    public static final int MAX_USERNAME_SIZE = 15;
    public static final int MAX_EMAIL_SIZE = 40;
    public static final int MIN_PASSWORD_SIZE = 6;
    public static final int MAX_PASSWORD_SIZE = 20;

    @NotBlank
    @Size(min = MIN_NAME_SIZE, max = MAX_NAME_SIZE)
    private String name;
    @NotBlank
    @Size(min = MIN_USERNAME_SIZE, max = MAX_USERNAME_SIZE)
    private String username;
    @NotBlank
    @Size(max = MAX_EMAIL_SIZE)
    private String email;
    @NotBlank
    @Size(min = MIN_PASSWORD_SIZE, max = MAX_PASSWORD_SIZE)
    private String password;

    public SingUpRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
