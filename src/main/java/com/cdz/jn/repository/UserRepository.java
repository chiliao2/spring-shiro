package com.cdz.jn.repository;

import com.cdz.jn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String userName);
}
