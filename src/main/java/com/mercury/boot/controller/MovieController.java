package com.mercury.boot.controller;


import com.mercury.boot.bean.Schedule;
import com.mercury.boot.service.MovieService;
import com.mercury.boot.service.RoomService;
import com.mercury.boot.service.ScheduleService;
import com.mercury.boot.utils.Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    final MovieService movieService;
    final ScheduleService scheduleService;
    final RoomService roomService;

    public MovieController(MovieService movieService, ScheduleService scheduleService, RoomService roomService) {
        this.movieService = movieService;
        this.scheduleService = scheduleService;
        this.roomService = roomService;
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
//    @ResponseBody
    public Map<String, Object> movieSchedule(HttpServletRequest request) {
        String movieId = request.getParameter("id");
        List<Schedule> schedules = scheduleService.getScheduleByMovieId(Integer.parseInt(movieId));
        List<Object> result = new LinkedList<>();
        for (Schedule s :
                schedules) {
            Map<String, Object> map = Utils.beanToMap(s);

            map.put("movie", Utils.beanToMap(movieService.getMovieById(Integer.parseInt(map.get("movieId").toString()))));
            map.put("room", Utils.beanToMap(movieService.getMovieById(Integer.parseInt(map.get("roomId").toString()))));
            map.remove("movieId");
            map.remove("roomId");
            result.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", result);
        return map;
    }

}
