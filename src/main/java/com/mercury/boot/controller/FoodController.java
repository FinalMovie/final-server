package com.mercury.boot.controller;

import com.mercury.boot.bean.Food;
import com.mercury.boot.service.FoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/api/deleteFood")
    @ResponseBody
    public Map<String, Object> deleteFood(HttpServletRequest request) {
        String foodId = request.getParameter("id");
        foodService.deleteFood(Long.parseLong(foodId));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/api/editFood")
    @ResponseBody
    public Map<String, Object> editFood(HttpServletRequest request) {
        String foodId = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String calories = request.getParameter("calories");
        String image = request.getParameter("image");
        Food food = new Food(Long.parseLong(foodId), name, Integer.parseInt(price), Integer.parseInt(calories), image);
        food = foodService.saveFood(food);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", food);
        return map;
    }

    @RequestMapping("/api/addFood")
    @ResponseBody
    public Map<String, Object> addFood(HttpServletRequest request) {
        long foodId = System.currentTimeMillis();
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String calories = request.getParameter("calories");
        String image = request.getParameter("image");
        Food food = new Food(foodId, name, Integer.parseInt(price), Integer.parseInt(calories), image);
        food = foodService.saveFood(food);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", food);
        return map;
    }

}
