package com.mercury.boot.dao;

import com.mercury.boot.bean.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
}
