package com.mercury.boot.dao;

import com.mercury.boot.bean.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDao extends JpaRepository<Food, Long> {

}
