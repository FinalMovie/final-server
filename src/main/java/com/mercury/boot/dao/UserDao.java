package com.mercury.boot.dao;

import com.mercury.boot.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
