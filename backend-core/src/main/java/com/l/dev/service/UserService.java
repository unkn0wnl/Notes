package com.l.dev.service;

import com.l.dev.entity.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void deleteUserById(Long id);

    List<User> findAll();
}
