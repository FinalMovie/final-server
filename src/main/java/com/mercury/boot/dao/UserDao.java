package com.mercury.boot.dao;

import com.mercury.boot.bean.Customers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<Customers,Long> {

    Customers findByUsername(String username);
}
