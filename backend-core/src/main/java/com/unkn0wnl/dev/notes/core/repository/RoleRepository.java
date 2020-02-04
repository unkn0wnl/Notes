package com.unkn0wnl.dev.notes.core.repository;

import com.unkn0wnl.dev.notes.core.entity.model.Role;
import com.unkn0wnl.dev.notes.core.entity.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum roleEnum);
}
