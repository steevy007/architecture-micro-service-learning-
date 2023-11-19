package com.steevelinformaticien.customerservice;

import com.steevelinformaticien.customerservice.entities.Customer;
import com.steevelinformaticien.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args->{
            customerRepository.save(Customer.builder().firstname("Steeve").lastname("Sanon").email("Sanon@gmail.com").build());
            customerRepository.save(Customer.builder().firstname("Esmer").lastname("Laurent").email("Laurent@gmail.com").build());
            customerRepository.save(Customer.builder().firstname("Jacque").lastname("Marc").email("Marc@gmail.com").build());
            customerRepository.save(Customer.builder().firstname("Joseph").lastname("Jean").email("Jean@gmail.com").build());
        };
    }
}
