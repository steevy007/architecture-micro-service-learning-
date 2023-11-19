package com.steevelinformaticien.accountservice.controllers;

import com.steevelinformaticien.accountservice.entities.BankAccount;
import com.steevelinformaticien.accountservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountRepository bankAccountRepository;

    @GetMapping(path = "/accounts")
    public List<BankAccount> getAllBankAccount(){
        return  bankAccountRepository.findAll();
    }

    @GetMapping(path = "/account/{id}")
    public BankAccount getAccountById(@PathVariable String id){
        return bankAccountRepository.findById(id).get();
    }

}
