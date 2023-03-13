package com.example.demo.service;

import com.example.demo.config.JWTServices;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.Role;
import com.example.demo.error.AccountAlreadyExistsException;
import com.example.demo.error.AccountNotFoundException;
import com.example.demo.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JWTServices jwtServices;

    @Transactional
    public AuthenticationResponse signup(@NotNull RegisterRequest registerRequest) throws AccountAlreadyExistsException {

        Optional<Account> foundAccount = accountRepository.findByUserNameOrEmail(registerRequest.getUserName(), registerRequest.getEmail());
        if (foundAccount.isPresent()) {
            throw new AccountAlreadyExistsException("Username or email already exists");
        }

        Account account = Account.builder()
                .userName(registerRequest.getUserName())
                .password((passwordEncoder.encode(registerRequest.getPassword())))
                .email(registerRequest.getEmail())
                .created(Instant.now())
                .role(Role.USER)
                .build();

        accountRepository.save(account);
        var token = jwtServices.generateNoClaimsToken(account);
        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws AccountNotFoundException {

        Optional<Account> foundAccount = accountRepository.findByUserNameOrEmail(loginRequest.getUserName(), loginRequest.getUserName());

        if (foundAccount.isPresent()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            var token = jwtServices.generateNoClaimsToken(foundAccount.get());
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();


        } else {
             throw new AccountNotFoundException("Account not found");
        }

    }
}
