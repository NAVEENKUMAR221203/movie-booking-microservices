package com.example.JwtAuthentication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JwtAuthentication.Model.Role;
import com.example.JwtAuthentication.enums.ERole;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(ERole name);
    
}
