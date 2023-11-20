package com.steevelinformaticien.accountservice.clients;

import com.steevelinformaticien.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/customer/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping(path = "/customers")
    @CircuitBreaker(name = "customerServices",fallbackMethod = "getAllCustomers")
    List<Customer> customerList();


    default  Customer getDefaultCustomer(Long id , Exception exception){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstname("Not Available");
        customer.setLastname("Not Available");
        customer.setEmail("Not Available");
        return customer;
    }

    default List<Customer> getAllCustomers(Exception exception){
        return List.of();
    }

}
