package com.mercury.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "Index Page.";
    }

}
