package com.steevelinformaticien.accountservice;

import com.steevelinformaticien.accountservice.clients.CustomerRestClient;
import com.steevelinformaticien.accountservice.entities.BankAccount;
import com.steevelinformaticien.accountservice.enums.AccountType;
import com.steevelinformaticien.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository , CustomerRestClient customerRestClient){
		return args->{

			System.out.println("VALUE "+customerRestClient.customerList().size());

			customerRestClient.customerList().forEach(c->{
				BankAccount bankAccount=BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.currency("USD")
						.sold(Math.random()*80000)
						.createdAt(LocalDate.now())
						.accountType(AccountType.SAVING_ACCOUNT)
						.customerId(c.getId())
						.build();

				BankAccount bankAccount1=BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.currency("HTG")
						.sold(Math.random()*80000)
						.createdAt(LocalDate.now())
						.accountType(AccountType.CURRENT_ACCOUNT)
						.customerId(c.getId())
						.build();




				List<BankAccount> bankAccounts=new ArrayList<>();
				bankAccounts.add(bankAccount);
				bankAccounts.add(bankAccount1);
				bankAccountRepository.saveAll(bankAccounts);

			});

		};
	}

}
