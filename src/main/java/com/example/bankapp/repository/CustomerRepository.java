package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
    Optional<Customer> findByCustomerId(Long customerId);
    void deleteById(Long customerId);
}
