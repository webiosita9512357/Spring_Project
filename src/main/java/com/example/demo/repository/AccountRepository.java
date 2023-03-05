package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findByUserNameIgnoreCaseContaining(String name);

    public Account findByUserNameOrEmail(String userName, String email);
}
