package com.example.bankapp.controller;

import com.example.bankapp.dto.CustomerDto;
import com.example.bankapp.dto.ResponseDto;
import com.example.bankapp.service.IAccountsService;
import com.example.bankapp.service.impl.AccountsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = (MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
public class AccountsController {
    @Autowired
    private AccountsServiceImpl accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(CustomerDto customerDto) {
        accountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Account created"));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccoutDetails(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Account updated"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("201", "Account creation failed"));

    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Account deleted"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("201", "Account creation failed"));

    }
}
