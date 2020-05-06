package com.mercury.boot.service;

import com.mercury.boot.bean.Movies;
import com.mercury.boot.dao.MovieDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {


    final MovieDao movieDao;


    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movies> getAllMovies() {
        return movieDao.findAll();
    }

    public Movies getMovieById(long id) {
        return movieDao.getOne(id);
    }

}
