package com.mercury.boot.controller;

import com.mercury.boot.service.FoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FoodController {

    final FoodService foodService;

    public FoodController(FoodService foodService) {

        this.foodService = foodService;
    }

    @RequestMapping("/api/foodList")
    @ResponseBody
    public Map<String, Object> foodList() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", foodService.getAllFood());
        return map;
    }

}
