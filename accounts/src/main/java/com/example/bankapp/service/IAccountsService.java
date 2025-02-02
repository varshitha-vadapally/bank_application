package com.example.bankapp.service;

import com.example.bankapp.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto) ;
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccoutDetails(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
