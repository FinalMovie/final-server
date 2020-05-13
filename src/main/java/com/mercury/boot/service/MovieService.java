package com.mercury.boot.service;

import com.mercury.boot.bean.Movies;
import com.mercury.boot.dao.MovieDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {


    final MovieDao movieDao;


    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Page<Movies> getAllMoviesPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return movieDao.findAll(pageable);
    }

    public List<Movies> getAllMovies() {
        return movieDao.findAll();
    }

    public Movies getMovieById(long id) {
        return movieDao.getOne(id);
    }

    public void deleteMovie(long id) {
        movieDao.deleteById(id);
    }

    public Movies saveMovie(Movies movie) {
        return movieDao.save(movie);
    }

}
