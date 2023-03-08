package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setUserName(registerRequest.getUserName());
        account.setPassword((passwordEncoder.encode(registerRequest.getPassword())));
        account.setEmail(registerRequest.getEmail());
        account.setCreated(Instant.now());

        accountRepository.save(account);

    }
}
