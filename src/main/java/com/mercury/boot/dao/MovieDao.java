package com.mercury.boot.dao;

import com.mercury.boot.bean.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDao extends JpaRepository<Movies, Long> {
    List<Movies> findAll();
}
