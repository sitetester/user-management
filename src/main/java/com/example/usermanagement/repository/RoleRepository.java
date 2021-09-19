package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String role_admin);

    boolean existsByName(String role_admin);
}