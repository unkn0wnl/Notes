package com.l.dev.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registerNewUser(String name, String username, String email, String hashedPassword);

}
