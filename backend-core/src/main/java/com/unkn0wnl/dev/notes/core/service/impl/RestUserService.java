package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.entity.model.Role;
import com.unkn0wnl.dev.notes.core.entity.model.RoleEnum;
import com.unkn0wnl.dev.notes.core.entity.model.User;
import com.unkn0wnl.dev.notes.core.repository.RoleRepository;
import com.unkn0wnl.dev.notes.core.repository.UserRepository;
import com.unkn0wnl.dev.notes.core.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class RestUserService implements UserService {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(RestUserService.class);
    }

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
                .orElseThrow(() -> new RuntimeException("User Role not set."));
        newUser.setRoles(Collections.singleton(userRole));

        userRepository.save(newUser);
    }

    @Override
    public boolean checkUserAvailability(String checkByType, String value) {
        boolean result = true;
        String normalizedCheckBy = checkByType.trim().toLowerCase();

        if (normalizedCheckBy.equals("username")) {
            result = userRepository.existsByUsername(value);
        } else if (normalizedCheckBy.equals("email")) {
            result = userRepository.existsByEmail(value);
        } else {
            throw new IllegalArgumentException("Invalid checkByType parameter. " + checkByType);
        }
        return result;
    }

}
