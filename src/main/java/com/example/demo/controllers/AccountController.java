package com.example.demo.controllers;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getHelloWorld() {
        return "hello world";
    }

    @PostMapping("/account")
    public Account postUser(@RequestBody Account user) {
        return accountService.saveAccount(user);
    }



}
