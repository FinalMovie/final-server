package com.mercury.boot.controller;


import com.mercury.boot.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    MovieService movieService;
    
}
