package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.error.AccountAlreadyExistsException;
import com.example.demo.error.AccountNotFoundException;

import java.util.List;

public interface AccountService {
    public Account saveAccount(Account user) throws AccountAlreadyExistsException;

    public List<Account> getAccounts();

    public Account getAccount(Long id) throws AccountNotFoundException;

    public void deleteAccount(Long id);

    public Account updateAccount(Long id, Account user);

    public List<Account> searchByNameAccounts(String name);
}
