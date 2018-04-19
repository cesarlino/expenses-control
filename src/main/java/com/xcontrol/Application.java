package com.xcontrol;

import com.xcontrol.model.Account;
import com.xcontrol.model.Category;
import com.xcontrol.model.Transaction;
import com.xcontrol.repository.AccountRepository;
import com.xcontrol.repository.CategoryRepository;
import com.xcontrol.repository.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * Main application class which is auto configured by Spring. It adds default values in the main method
 * and sets the base URI of the REST endpoint to "/api".
 */
@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class Application extends RepositoryRestConfigurerAdapter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);
        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);

        // save a couple of categories
        Category household = categoryRepository.save(Category.from("Household", null, new ArrayList<>()));
        categoryRepository.save(Category.from("Market", household, new ArrayList<>()));
        categoryRepository.save(Category.from("Internet", household, new ArrayList<>()));
        categoryRepository.save(Category.from("Health", null, new ArrayList<>()));
        Category entertainment = categoryRepository.save(Category.from("Entertainment", null, new ArrayList<>()));
        categoryRepository.save(Category.from("Dinning Out", entertainment, new ArrayList<>()));
        Category pub = categoryRepository.save(Category.from("Pub", entertainment, new ArrayList<>()));

        Account current = accountRepository.save(Account.from("Current Account"));
        accountRepository.save(Account.from("Savings Account"));

        // save a couple of items
        transactionRepository.save(Transaction.from("Lidl", LocalDate.of(2018, Month.APRIL, 10), new BigDecimal(50.00), household, current));
        transactionRepository.save(Transaction.from("Tesco", LocalDate.of(2018, Month.APRIL, 14), new BigDecimal(27.00),household, current));
        transactionRepository.save(Transaction.from("Cineworld", LocalDate.of(2018, Month.APRIL, 15), new BigDecimal(6.50), entertainment, current));
        transactionRepository.save(Transaction.from("Pit Bros", LocalDate.of(2018, Month.APRIL, 15), new BigDecimal(45.00), entertainment, current));
        transactionRepository.save(Transaction.from("Schoolhouse", LocalDate.of(2018, Month.APRIL, 13), new BigDecimal(11.00), pub, current));
        transactionRepository.save(Transaction.from("Ginger man", LocalDate.of(2018, Month.APRIL, 13), new BigDecimal(10.00), pub, current));
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
    }

}