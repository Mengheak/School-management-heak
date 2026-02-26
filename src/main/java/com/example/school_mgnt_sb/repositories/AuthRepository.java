package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.auth.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findByEmail(String email);
    Boolean existsByEmail(String email);
}
