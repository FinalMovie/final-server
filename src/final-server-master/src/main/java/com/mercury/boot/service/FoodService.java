package com.mercury.boot.service;


import com.mercury.boot.bean.Food;
import com.mercury.boot.dao.FoodDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodService {

    final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public List<Food> getAllFood() {

        return foodDao.findAll();
    }
}
