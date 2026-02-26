package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Boolean existsByName(String name);
}
