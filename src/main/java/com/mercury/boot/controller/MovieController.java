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
    @ResponseBody
    public Map<String, Object> movieSchedule(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<Object> result = new LinkedList<>();
        try {
            String movieId = request.getParameter("id");
            List<Schedule> schedules = scheduleService.getScheduleByMovieId(Integer.parseInt(movieId));
            for (Schedule s :
                    schedules) {
                Map<String, Object> m = Utils.beanToMap(s);
                m.put("movie", Utils.beanToMap(movieService.getMovieById(Integer.parseInt(map.get("movieId").toString()))));
                m.put("room", Utils.beanToMap(movieService.getMovieById(Integer.parseInt(map.get("roomId").toString()))));
                m.remove("movieId");
                m.remove("roomId");
                result.add(m);
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", e.toString());
            return map;
        }
        map.put("success", true);
        map.put("data", result);
        return map;
    }
}
