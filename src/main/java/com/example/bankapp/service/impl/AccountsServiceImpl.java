package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountsDto;
import com.example.bankapp.dto.CustomerDto;
import com.example.bankapp.entity.Accounts;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.exception.CustomerAlreadyExistsException;
import com.example.bankapp.exception.ResourceNotFoundException;
import com.example.bankapp.mapper.AccountsMapper;
import com.example.bankapp.mapper.CustomerMapper;
import com.example.bankapp.repository.AccountsRepository;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer =  CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists with the same mobile number");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");
        System.out.println(customer.getName());
        System.out.println(customer.getCreatedBy());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getcustomerId());
        newAccount.setAccountNumber(1000000000L + new Random().nextInt(900000000));
        newAccount.setAccountType("savings");
        newAccount.setBranchAddress("address");
        newAccount.setCreatedBy("anonymous");
        return newAccount;
    }

    public CustomerDto fetchAccount(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber));
        Accounts accounts=accountsRepository.findByCustomerId(customer.getcustomerId()).orElseThrow(()->new ResourceNotFoundException("Account","Mobile Number",mobileNumber));
        CustomerDto customerDto = new CustomerDto();
        CustomerMapper.mapToCustomerDto(customer,customerDto);
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccoutDetails(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Accounts accounts ;
        if(accountsDto!=null){
            accounts = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber()).orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accounts,accountsDto);
            accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findByCustomerId(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            return true;
        }
        return false;


    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Customer",
        "mobileNumber",mobileNumber));
        Long customerId = customer.getcustomerId();
        accountsRepository.deleteByCustomerId(customerId);
        customerRepository.deleteById(customerId);
        return true;
    }
}
