package com.l.dev.service.impl;

import com.l.dev.entity.model.User;
import com.l.dev.repository.UserRepository;
import com.l.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleRestUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
