package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.error.AccountAlreadyExistsException;
import com.example.demo.error.AccountNotFoundException;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account user) throws AccountAlreadyExistsException {
        Optional<Account> account = Optional.ofNullable(accountRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail()));
        if (account.isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists for this username or email: " + user.getUserName() + " or " + user.getEmail());
        }
        return accountRepository.save(user);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) throws AccountNotFoundException {
        Optional<Account> account =  accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account not found for id: " + id);
        }

        return account.get();
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

        if (Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
            account.setUserName(user.getUserName());
        }

        return accountRepository.save(account);
    }

    @Override
    public List<Account> searchByNameAccounts(String name) {
        return accountRepository.findByUserNameIgnoreCaseContaining(name);
    }
}
