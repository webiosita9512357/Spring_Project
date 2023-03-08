package com.example.demo.controllers;

import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class DashboardController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
