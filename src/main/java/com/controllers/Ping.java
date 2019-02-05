package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ryandesmond - https://codingnomads.co
 */
@RestController
@RequestMapping("/ping")
public class Ping {

    /**
     * Simple end-point for testing that API is alive and well
     *
     * @return String "success" if API is alive and well
     */
    @GetMapping
    public String test(){
        return "success";
    }

}