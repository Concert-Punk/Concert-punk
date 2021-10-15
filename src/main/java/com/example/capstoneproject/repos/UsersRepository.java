package com.example.capstoneproject.repos;

import com.example.capstoneproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
