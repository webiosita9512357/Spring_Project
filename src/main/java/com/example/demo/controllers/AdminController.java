package com.example.demo.controllers;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.error.AccountAlreadyExistsException;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
