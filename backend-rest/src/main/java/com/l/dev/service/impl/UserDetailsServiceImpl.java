package com.l.dev.service.impl;

import com.l.dev.entity.model.User;
import com.l.dev.repository.UserRepository;
import com.l.dev.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {
        super();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );
        return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), new HashSet<>());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with id %d not found!", id))
                );
        return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getName(), new HashSet<>());
    }
}
