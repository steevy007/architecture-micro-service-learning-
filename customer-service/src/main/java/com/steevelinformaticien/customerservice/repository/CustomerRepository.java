package com.steevelinformaticien.customerservice.repository;

import com.steevelinformaticien.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
