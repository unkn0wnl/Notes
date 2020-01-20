package com.l.dev.payload.request;

import org.hibernate.validator.constraints.NotBlank;


public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;

    public LoginRequest() {
        super();
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "usernameOrEmail='" + usernameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
