package com.example.projectboard.repository;

import com.example.projectboard.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

     Optional<UserAccount> findByUserId(String userId);

}