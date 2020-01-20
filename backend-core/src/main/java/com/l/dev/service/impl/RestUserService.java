package com.l.dev.service.impl;

import com.l.dev.entity.model.Role;
import com.l.dev.entity.model.RoleEnum;
import com.l.dev.entity.model.User;
import com.l.dev.repository.RoleRepository;
import com.l.dev.repository.UserRepository;
import com.l.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RestUserService implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public RestUserService() {
        super();
    }

    @Autowired
    public RestUserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerNewUser(String name, String username, String email, String hashedPassword) {
        User newUser = new User(name, username, email, hashedPassword);

        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(()-> new RuntimeException("User Role not set."));
        newUser.setRoles(Collections.singleton(userRole));

        userRepository.save(newUser);
    }
}
