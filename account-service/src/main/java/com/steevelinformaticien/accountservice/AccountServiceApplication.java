package com.steevelinformaticien.accountservice;

import com.steevelinformaticien.accountservice.entities.BankAccount;
import com.steevelinformaticien.accountservice.enums.AccountType;
import com.steevelinformaticien.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository){
		return args->{
			BankAccount bankAccount=BankAccount.builder()
					.id(UUID.randomUUID().toString())
					.currency("USD")
					.sold(90978)
					.createdAt(LocalDate.now())
					.accountType(AccountType.SAVING_ACCOUNT)
					.customerId(Long.valueOf(1))
					.build();

			BankAccount bankAccount1=BankAccount.builder()
					.id(UUID.randomUUID().toString())
					.currency("HTG")
					.sold(1000000)
					.createdAt(LocalDate.now())
					.accountType(AccountType.CURRENT_ACCOUNT)
					.customerId(Long.valueOf(2))
					.build();


			BankAccount bankAccount2=BankAccount.builder()
					.id(UUID.randomUUID().toString())
					.currency("CAD")
					.sold(76788)
					.createdAt(LocalDate.now())
					.accountType(AccountType.SAVING_ACCOUNT)
					.customerId(Long.valueOf(2))
					.build();

			List<BankAccount> bankAccounts=new ArrayList<>();
			bankAccounts.add(bankAccount);
			bankAccounts.add(bankAccount1);
			bankAccounts.add(bankAccount2);

			bankAccountRepository.saveAll(bankAccounts);
		};
	}

}
