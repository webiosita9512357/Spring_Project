package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Account;
import com.example.demo.error.AccountAlreadyExistsException;
import com.example.demo.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;

    @Transactional
    public void signup(@NotNull RegisterRequest registerRequest) throws AccountAlreadyExistsException {

        Account foundAccount = accountRepository.findByUserNameOrEmail(registerRequest.getUserName(), registerRequest.getEmail());
        if (foundAccount != null) {
            throw new AccountAlreadyExistsException("Username or email already exists");
        }

        Account account = new Account();
        account.setUserName(registerRequest.getUserName());
        account.setPassword((passwordEncoder.encode(registerRequest.getPassword())));
        account.setEmail(registerRequest.getEmail());
        account.setCreated(Instant.now());
        account.setRole("USER");

        accountRepository.save(account);
    }

    public void login(LoginRequest loginRequest) {

    }
}
