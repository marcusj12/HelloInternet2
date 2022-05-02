package com.careerdevs.HelloInternet2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Autowired

    @GetMapping("/hello")
    private String helloCareerDevs(){
        return "You requested the root route";
    }
}
