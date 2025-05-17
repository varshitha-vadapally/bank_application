package com.example.bankapp.controller;

import com.example.bankapp.dto.BuildVersionDto;
import com.example.bankapp.dto.CustomerDto;
import com.example.bankapp.dto.ResponseDto;
import com.example.bankapp.service.IAccountsService;
import com.example.bankapp.service.impl.AccountsServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = (MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
@Validated
public class AccountsController {
    @Autowired
    private AccountsServiceImpl accountsService;

    @Autowired
    private BuildVersionDto buildVersionDto;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount( @RequestBody   CustomerDto customerDto) {
        accountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Account created"));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "$|[0-9]{10}",message = "number invalid")
                                                               String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccoutDetails(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Account updated"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("201", "Account creation failed"));

    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "$|[0-9]{10}",message = "number invalid")
                                                         String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Account deleted"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("201", "Account creation failed"));

    }
    @GetMapping("/build-version")
    public ResponseEntity<BuildVersionDto> fetchAccountDetails(){
        System.out.println(buildVersionDto.getVersion());
        return ResponseEntity.status(HttpStatus.OK).body(buildVersionDto);
    }
}
