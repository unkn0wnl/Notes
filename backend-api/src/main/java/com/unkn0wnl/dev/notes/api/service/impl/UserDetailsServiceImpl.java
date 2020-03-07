package com.unkn0wnl.dev.notes.api.service.impl;

import com.unkn0wnl.dev.notes.api.security.principal.UserPrincipal;
import com.unkn0wnl.dev.notes.core.entity.model.User;
import com.unkn0wnl.dev.notes.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );
        Set<GrantedAuthority> authorities = this.collectUserRoles(user);
        return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with id %d not found!", id))
                );
        Set<GrantedAuthority> authorities = this.collectUserRoles(user);
        return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getName(), authorities);
    }

    private Set<GrantedAuthority> collectUserRoles(User user) {
        return user
                .getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().name())
                ).collect(Collectors.toSet());
    }

}