package com.mercury.boot.service;


import com.mercury.boot.bean.Food;
import com.mercury.boot.dao.FoodDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodService {

    final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public Page<Food> getAllFoodPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return foodDao.findAll(pageable);
    }

    public List<Food> getAllFood() {
        return foodDao.findAll();
    }

    public void deleteFood(long id) {
        foodDao.deleteById(id);
    }

    public Food saveFood(Food food) {
        return foodDao.save(food);
    }
}
