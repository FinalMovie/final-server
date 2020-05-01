package com.mercury.boot.controller;

import com.mercury.boot.service.FoodService;

public class FoodController {

    final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

}
