package com.steevelinformaticien.accountservice.controllers;

import com.steevelinformaticien.accountservice.clients.CustomerRestClient;
import com.steevelinformaticien.accountservice.entities.BankAccount;
import com.steevelinformaticien.accountservice.model.Customer;
import com.steevelinformaticien.accountservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping(path = "/accounts")
    public List<BankAccount> getAllBankAccount(){
        List<BankAccount> list=bankAccountRepository.findAll();
        list.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return  list;
    }

    @GetMapping(path = "/account/{id}")
    public BankAccount getAccountById(@PathVariable String id){
        BankAccount bankAccount= bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

}
