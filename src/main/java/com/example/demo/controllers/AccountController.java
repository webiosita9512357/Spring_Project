package com.example.demo.controllers;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getHelloWorld() {
        return "hello world";
    }

    @PostMapping("/account")
    public Account postUser(@Valid @RequestBody Account user) {
        return accountService.saveAccount(user);
    }

    @PutMapping("/account/{id}")
    public Account putUser(@PathVariable("id") Long id, @RequestBody Account user) {
        return accountService.updateAccount(id, user);
    }
    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        logger.info("In getAccount - AccountController");
        return accountService.getAccount(id);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        logger.info("In getAccounts - AccountController");
        return accountService.getAccounts();
    }

    @GetMapping("/account/searchUsers/{name}")
    public List<Account> searchUsers(@PathVariable("name") String name) {
        logger.info("In searchUsers - Searching for: " + name + " - AccountController");
        return accountService.searchByNameAccounts(name);
    }

    @DeleteMapping("/account/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
            accountService.getAccount(id);
            return "user deleted";
    }

}
