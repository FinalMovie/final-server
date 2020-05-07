package com.mercury.boot.controller;


import com.mercury.boot.bean.Movies;
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
            List<Schedule> schedules = scheduleService.getScheduleByMovieId(Long.parseLong(movieId));
            for (Schedule s :
                    schedules) {
                Map<String, Object> m = Utils.beanToMap(s);
                m.put("movie", Utils.beanToMap(movieService.getMovieById(Long.parseLong(m.get("movieId").toString()))));
                m.put("room", Utils.beanToMap(roomService.getRoomById(Long.parseLong(m.get("roomId").toString()))));
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

    @RequestMapping("/api/deleteMovie")
    @ResponseBody
    public Map<String, Object> deleteMovie(HttpServletRequest request) {
        String movieId = request.getParameter("id");
        movieService.deleteMovie(Long.parseLong(movieId));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/api/editMovie")
    @ResponseBody
    public Map<String, Object> editMovie(HttpServletRequest request) {
        String movieId = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String desc = request.getParameter("desc");
        String image = request.getParameter("image");
        Movies movie = new Movies(Long.parseLong(movieId), name, Integer.parseInt(price), desc, image);
        movieService.saveMovie(movie);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movie);
        return map;
    }

    @RequestMapping("/api/addMovie")
    @ResponseBody
    public Map<String, Object> addMovie(HttpServletRequest request) {
        long movieId = System.currentTimeMillis();
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String desc = request.getParameter("desc");
        String image = request.getParameter("image");
        Movies movie = new Movies(movieId, name, Integer.parseInt(price), desc, image);
        movieService.saveMovie(movie);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movie);
        return map;
    }
}
