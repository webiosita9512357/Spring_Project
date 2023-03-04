package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.List;

public interface AccountService {
    public Account saveAccount(Account user);

    public List<Account> getAccounts();

    public Account getAccount(Long id);

    public void deleteAccount(Long id);

    public Account updateAccount(Long id, Account user);
}
