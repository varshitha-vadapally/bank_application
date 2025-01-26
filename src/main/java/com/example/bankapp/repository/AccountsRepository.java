package com.example.bankapp.repository;

import com.example.bankapp.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    public Optional<Accounts> findByCustomerId(Long CustomerId);
    public Optional<Accounts> findByAccountNumber(Long AccountNumber);
    @Transactional
    @Modifying
    public void deleteByCustomerId(Long CustomerId);

}
