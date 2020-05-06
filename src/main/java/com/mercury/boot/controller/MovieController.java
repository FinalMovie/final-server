package com.mercury.boot.controller;


import com.mercury.boot.bean.Schedule;
import com.mercury.boot.service.MovieService;
import com.mercury.boot.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    final MovieService movieService;
    final ScheduleService scheduleService;

    public MovieController(MovieService movieService, ScheduleService scheduleService) {
        this.movieService = movieService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping("/api/movieList")
    @ResponseBody
    public Map<String, Object> moviesList() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movieService.getAllMovies());
        return map;
    }

    @RequestMapping("/api/movieSchedule")
    @ResponseBody
    public Map<String, Object> movieSchedule(HttpServletRequest request) {
        String movieId = request.getParameter("id");
        List<Schedule> result = scheduleService.getScheduleByMovieId(Integer.parseInt(movieId));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", result);
        return map;
    }

}
