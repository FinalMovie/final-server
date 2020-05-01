package com.mercury.boot.dao;

import com.mercury.boot.bean.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDao extends JpaRepository<Food, Long> {
    List<Food> findAll();

}
