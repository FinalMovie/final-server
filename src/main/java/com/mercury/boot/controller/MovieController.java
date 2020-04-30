package com.mercury.boot.controller;


import com.mercury.boot.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {

    final
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/api/movieList")
    @ResponseBody
    public Map<String, Object> moviesList() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movieService.getAllMovies());
        return map;
    }

}
