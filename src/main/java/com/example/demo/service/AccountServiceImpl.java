package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account user) {
        return accountRepository.save(user);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account updateAccount(Long id, Account user) {
        Account account = accountRepository.findById(id).get();

        if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
            account.setName(user.getName());
        }

        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            account.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            account.setEmail(user.getEmail());
        }

        return accountRepository.save(account);
    }
}
