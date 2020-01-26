package com.unkn0wnl.dev.notes.core.repository;

import com.unkn0wnl.dev.notes.core.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin
@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}